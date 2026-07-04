package com.fup.membermanagement.backend_api.repository;

import com.fup.membermanagement.backend_api.model.dto.AuditoriaDTO;
import com.fup.membermanagement.backend_api.model.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    @Query("SELECT new com.fup.membermanagement.backend_api.model.dto.AuditoriaDTO(" +
            "a.idLog, a.fechaHora, u.username, a.accion, a.detalle, m.numCedula) " +
            "FROM AuditLog a JOIN a.usuario u LEFT JOIN Miembro m ON a.registroId = m.idMiembro " +
            "WHERE a.tablaAfectada = 'MIEMBROS' AND a.accion IN ('CREATE', 'UPDATE') " +
            "ORDER BY a.fechaHora DESC")
    List<AuditoriaDTO> findAuditoriaMiembros();
}