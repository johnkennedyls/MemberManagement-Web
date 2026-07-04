package com.fup.membermanagement.backend_api.repository;

import com.fup.membermanagement.backend_api.model.entity.User;
import com.fup.membermanagement.backend_api.model.entity.UserEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT R.NOMBRE_ROLE, U.USERNAME, COUNT(M.ID_MIEMBRO) FROM USUARIOS U JOIN ROLES R ON U.ID_ROLE = R.ID_ROLE LEFT JOIN MIEMBROS M ON U.ID_USUARIO = M.ID_USUARIO_REGISTRA WHERE U.ESTADO = 'ACTIVO' GROUP BY R.NOMBRE_ROLE, U.USERNAME ORDER BY COUNT(M.ID_MIEMBRO) DESC", nativeQuery = true)
    List<Object[]> findUsuariosPorRolConMiembrosNative();

    @Query(value = "SELECT COUNT(*) FROM USUARIOS WHERE ESTADO = 'ACTIVO'", nativeQuery = true)
    Long countUsuariosActivosNative();

    List<User> findByEstado(UserEstado estado);
}