package com.fup.membermanagement.backend_api.controller;

import com.fup.membermanagement.backend_api.model.dto.AuditoriaDTO;
import com.fup.membermanagement.backend_api.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/auditoria")
@RequiredArgsConstructor
public class AuditController {

    private final AuditLogService auditLogService;

    @GetMapping("/miembros")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AuditoriaDTO>> obtenerAuditoriaMiembros() {
        return ResponseEntity.ok(auditLogService.findAuditoriaMiembros());
    }
}