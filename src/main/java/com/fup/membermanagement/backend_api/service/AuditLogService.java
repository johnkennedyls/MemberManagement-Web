package com.fup.membermanagement.backend_api.service;

import com.fup.membermanagement.backend_api.model.dto.AuditoriaDTO;
import com.fup.membermanagement.backend_api.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public List<AuditoriaDTO> findAuditoriaMiembros() {
        return auditLogRepository.findAuditoriaMiembros();
    }
}