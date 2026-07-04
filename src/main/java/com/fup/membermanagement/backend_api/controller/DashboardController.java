package com.fup.membermanagement.backend_api.controller;

import com.fup.membermanagement.backend_api.model.dto.AlertaPastoralDTO;
import com.fup.membermanagement.backend_api.model.dto.DashboardResumenDTO;
import com.fup.membermanagement.backend_api.model.dto.InformePastoralDTO;
import com.fup.membermanagement.backend_api.service.HuggingFaceService;
import com.fup.membermanagement.backend_api.service.MiembroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final MiembroService miembroService;
    private final HuggingFaceService huggingFaceService;

    @GetMapping("/resumen")
    public ResponseEntity<DashboardResumenDTO> obtenerResumenDashboard() {
        return ResponseEntity.ok(miembroService.findResumenDashboard());
    }

    @GetMapping("/alertas/ia")
    public ResponseEntity<List<AlertaPastoralDTO>> obtenerAlertasInteligentes() {
        Map<String, Object> datos = miembroService.obtenerDatosParaIA();
        return ResponseEntity.ok(huggingFaceService.generarAlertasAsistencia(datos));
    }

    @PostMapping("/informe/generar")
    public ResponseEntity<InformePastoralDTO> generarInformePastoral() {
        Map<String, Object> datos = miembroService.obtenerDatosParaIA();
        // Agregar datos adicionales para el informe
        datos.put("asistencia_promedio", 45L); // Ejemplo: promedio semanal
        return ResponseEntity.ok(huggingFaceService.generarInformePastoral(datos));
    }
}