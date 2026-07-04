package com.fup.membermanagement.backend_api.service;

import com.fup.membermanagement.backend_api.model.dto.*;
import com.fup.membermanagement.backend_api.model.entity.Miembro;
import com.fup.membermanagement.backend_api.repository.MiembroRepository;
import com.fup.membermanagement.backend_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MiembroService {

    private final MiembroRepository miembroRepository;
    private final UsuarioRepository usuarioRepository;

    public List<MiembroRegistradorDTO> findMiembrosActivosConRegistrador() {
        return miembroRepository.findMiembrosActivosConRegistradorNative().stream()
                .map(row -> new MiembroRegistradorDTO(
                        ((Number) row[0]).longValue(),
                        (String) row[1],
                        (String) row[2],
                        (String) row[3],
                        (String) row[4],
                        (String) row[5]
                ))
                .collect(Collectors.toList());
    }

    public List<ConteoDTO> countMiembrosPorEstadoYGenero() {
        return miembroRepository.countMiembrosPorEstadoYGeneroNative().stream()
                .map(row -> new ConteoDTO(
                        (String) row[0],
                        (String) row[1],
                        ((Number) row[2]).longValue()
                ))
                .collect(Collectors.toList());
    }

    public List<MiembroDTO> findVigenciaProximaAVencer(LocalDate fechaLimite) {
        return miembroRepository.findVigenciaProximaAVencerNative(fechaLimite)
                .stream().map(MiembroDTO::fromEntity).collect(Collectors.toList());
    }

    public List<MiembroDTO> findNuevosMiembrosUltimoMes(LocalDate fechaInicio) {
        return miembroRepository.findNuevosMiembrosUltimoMesNative(fechaInicio)
                .stream().map(MiembroDTO::fromEntity).collect(Collectors.toList());
    }

    public List<MiembroDTO> findMiembrosBajaReciente(LocalDate fechaInicio) {
        return miembroRepository.findMiembrosBajaRecienteNative(fechaInicio)
                .stream().map(MiembroDTO::fromEntity).collect(Collectors.toList());
    }

    public List<ConteoDTO> countMiembrosPorLugarExpedicion() {
        return miembroRepository.countMiembrosPorLugarExpedicionNative().stream()
                .map(row -> new ConteoDTO(
                        (String) row[0],
                        null,
                        ((Number) row[2]).longValue()
                ))
                .collect(Collectors.toList());
    }

    public DashboardResumenDTO findResumenDashboard() {
        Long activos = miembroRepository.countMiembrosActivosNative();
        Long nuevos = miembroRepository.countNuevosEsteMesNative();
        Long usuarios = usuarioRepository.countUsuariosActivosNative();

        return new DashboardResumenDTO(
                activos != null ? activos : 0L,
                nuevos != null ? nuevos : 0L,
                usuarios != null ? usuarios : 0L
        );
    }

    @Transactional
    public MiembroDTO createMiembro(Miembro miembro) {
        miembro.setFechaRegistro(LocalDateTime.now());
        Miembro saved = miembroRepository.save(miembro);
        return MiembroDTO.fromEntity(saved);
    }

    public Map<String, Object> obtenerDatosParaIA() {
        Map<String, Object> datos = new HashMap<>();

        Long activos = miembroRepository.countMiembrosActivosNative();
        Long nuevos = miembroRepository.countNuevosEsteMesNative();

        datos.put("totalActivos", activos != null ? activos : 0L);
        datos.put("nuevosEsteMes", nuevos != null ? nuevos : 0L);
        datos.put("porEstado", countMiembrosPorEstadoYGenero());
        datos.put("porLugar", countMiembrosPorLugarExpedicion());

        return datos;
    }
}