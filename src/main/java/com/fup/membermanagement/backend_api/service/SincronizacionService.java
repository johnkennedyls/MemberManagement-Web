package com.fup.membermanagement.backend_api.service;

import com.fup.membermanagement.backend_api.model.dto.SincronizacionDTO;
import com.fup.membermanagement.backend_api.repository.SincronizacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SincronizacionService {

    private final SincronizacionRepository sincronizacionRepository;

    public List<SincronizacionDTO> findMiembrosConSincronizacionPendiente() {
        return sincronizacionRepository.findMiembrosConSincronizacionPendienteNative().stream()
                .map(row -> new SincronizacionDTO(
                        ((Number) row[0]).longValue(),
                        (String) row[1],
                        (String) row[2],
                        (String) row[3],
                        ((Number) row[4]).intValue(),
                        (LocalDateTime) row[5]
                ))
                .collect(Collectors.toList());
    }
}