package com.fup.membermanagement.backend_api.controller;

import com.fup.membermanagement.backend_api.model.dto.*;
import com.fup.membermanagement.backend_api.model.entity.Miembro;
import com.fup.membermanagement.backend_api.service.MiembroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/miembros")
@RequiredArgsConstructor
public class MiembroController {

    private final MiembroService miembroService;

    @GetMapping("/activos-con-registrador")
    public ResponseEntity<List<MiembroRegistradorDTO>> obtenerMiembrosActivosConRegistrador() {
        return ResponseEntity.ok(miembroService.findMiembrosActivosConRegistrador());
    }

    @GetMapping("/estadisticas/miembros-por-estado-genero")
    public ResponseEntity<List<ConteoDTO>> obtenerEstadisticasDemograficas() {
        return ResponseEntity.ok(miembroService.countMiembrosPorEstadoYGenero());
    }

    @GetMapping("/alertas/vigencia-cedula-proxima")
    public ResponseEntity<List<MiembroDTO>> obtenerAlertasVigenciaCedula(
            @RequestParam(defaultValue = "6") Integer meses) {
        LocalDate limite = LocalDate.now().plusMonths(meses);
        return ResponseEntity.ok(miembroService.findVigenciaProximaAVencer(limite));
    }

    @GetMapping("/nuevos-ultimo-mes")
    public ResponseEntity<List<MiembroDTO>> obtenerNuevosMiembros(
            @RequestParam(defaultValue = "30") Integer dias) {
        LocalDate inicio = LocalDate.now().minusDays(dias);
        return ResponseEntity.ok(miembroService.findNuevosMiembrosUltimoMes(inicio));
    }

    @GetMapping("/baja-reciente")
    public ResponseEntity<List<MiembroDTO>> obtenerMiembrosBajaReciente(
            @RequestParam(defaultValue = "90") Integer dias) {
        LocalDate inicio = LocalDate.now().minusDays(dias);
        return ResponseEntity.ok(miembroService.findMiembrosBajaReciente(inicio));
    }

    @GetMapping("/estadisticas/miembros-por-lugar-expedicion")
    public ResponseEntity<List<ConteoDTO>> obtenerDistribucionGeografica() {
        return ResponseEntity.ok(miembroService.countMiembrosPorLugarExpedicion());
    }

    @GetMapping("/dashboard/resumen")
    public ResponseEntity<DashboardResumenDTO> obtenerResumenDashboard() {
        return ResponseEntity.ok(miembroService.findResumenDashboard());
    }

    @PostMapping
    public ResponseEntity<MiembroDTO> crearMiembro(@RequestBody Miembro miembro) {
        return ResponseEntity.status(201).body(miembroService.createMiembro(miembro));
    }

    @GetMapping("/datos-para-ia")
    public ResponseEntity<Map<String, Object>> obtenerDatosParaIA() {
        return ResponseEntity.ok(miembroService.obtenerDatosParaIA());
    }
}