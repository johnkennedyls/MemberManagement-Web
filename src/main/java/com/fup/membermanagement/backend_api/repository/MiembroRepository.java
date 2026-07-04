package com.fup.membermanagement.backend_api.repository;

import com.fup.membermanagement.backend_api.model.entity.Miembro;
import com.fup.membermanagement.backend_api.model.entity.MemberEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface MiembroRepository extends JpaRepository<Miembro, Long> {

    @Query(value = """
        SELECT M.ID_MIEMBRO, M.NUM_CEDULA, M.NOMBRES, M.APELLIDOS, M.ESTADO, U.USERNAME 
        FROM MIEMBROS M 
        JOIN USUARIOS U ON M.ID_USUARIO_REGISTRA = U.ID_USUARIO 
        WHERE M.ESTADO = 'ACTIVO'
        """, nativeQuery = true)
    List<Object[]> findMiembrosActivosConRegistradorNative();

    @Query(value = "SELECT COUNT(*) FROM MIEMBROS WHERE ESTADO = 'ACTIVO'", nativeQuery = true)
    Long countMiembrosActivosNative();

    @Query(value = """
        SELECT COUNT(*) FROM MIEMBROS 
        WHERE EXTRACT(YEAR FROM FECHA_REGISTRO) = EXTRACT(YEAR FROM CURRENT_DATE)
        AND EXTRACT(MONTH FROM FECHA_REGISTRO) = EXTRACT(MONTH FROM CURRENT_DATE)
        """, nativeQuery = true)
    Long countNuevosEsteMesNative();



    @Query(value = "SELECT ESTADO, GENERO, COUNT(*) FROM MIEMBROS GROUP BY ESTADO, GENERO ORDER BY COUNT(*) DESC", nativeQuery = true)
    List<Object[]> countMiembrosPorEstadoYGeneroNative();

    @Query(value = "SELECT * FROM MIEMBROS WHERE ESTADO = 'ACTIVO' AND VIGENCIA_CEDULA BETWEEN CURRENT_DATE AND :fechaLimite ORDER BY VIGENCIA_CEDULA ASC", nativeQuery = true)
    List<Miembro> findVigenciaProximaAVencerNative(@Param("fechaLimite") LocalDate fecha);

    @Query(value = "SELECT * FROM MIEMBROS WHERE FECHA_REGISTRO >= :fechaInicio ORDER BY FECHA_REGISTRO DESC", nativeQuery = true)
    List<Miembro> findNuevosMiembrosUltimoMesNative(@Param("fechaInicio") LocalDate fecha);

    @Query(value = "SELECT * FROM MIEMBROS WHERE ESTADO IN ('INACTIVO', 'ELIMINADO') AND FECHA_REGISTRO >= :fechaInicio", nativeQuery = true)
    List<Miembro> findMiembrosBajaRecienteNative(@Param("fechaInicio") LocalDate fecha);

    @Query(value = "SELECT LUGAR_EXPEDICION, NULL, COUNT(*) FROM MIEMBROS WHERE ESTADO = 'ACTIVO' GROUP BY LUGAR_EXPEDICION ORDER BY COUNT(*) DESC", nativeQuery = true)
    List<Object[]> countMiembrosPorLugarExpedicionNative();

    List<Miembro> findByEstado(MemberEstado estado);
}