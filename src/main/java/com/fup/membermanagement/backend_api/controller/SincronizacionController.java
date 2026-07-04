package com.fup.membermanagement.backend_api.controller;

import com.fup.membermanagement.backend_api.model.dto.SincronizacionDTO;
import com.fup.membermanagement.backend_api.service.SincronizacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sincronizacion")
@RequiredArgsConstructor
public class SincronizacionController {

    private final SincronizacionService sincronizacionService;

    @GetMapping("/pendientes")
    public ResponseEntity<List<SincronizacionDTO>> obtenerSincronizacionesPendientes() {
        return ResponseEntity.ok(sincronizacionService.findMiembrosConSincronizacionPendiente());
    }
}