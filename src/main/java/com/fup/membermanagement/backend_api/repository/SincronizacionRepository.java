package com.fup.membermanagement.backend_api.repository;

import com.fup.membermanagement.backend_api.model.entity.Sincronizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SincronizacionRepository extends JpaRepository<Sincronizacion, Long> {

    @Query(value = "SELECT M.ID_MIEMBRO, M.NUM_CEDULA, M.NOMBRES, S.ESTADO_SYNC, S.VERSION_REGISTRO, S.ULTIMA_ACTUALIZACION FROM SINCRONIZACION S JOIN MIEMBROS M ON S.ID_MIEMBRO = M.ID_MIEMBRO WHERE S.ESTADO_SYNC = 'PENDIENTE'", nativeQuery = true)
    List<Object[]> findMiembrosConSincronizacionPendienteNative();
}