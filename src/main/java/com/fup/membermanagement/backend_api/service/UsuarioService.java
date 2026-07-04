package com.fup.membermanagement.backend_api.service;

import com.fup.membermanagement.backend_api.model.dto.UsuarioMiembrosDTO;
import com.fup.membermanagement.backend_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<UsuarioMiembrosDTO> findUsuariosPorRolConMiembros() {
        return usuarioRepository.findUsuariosPorRolConMiembrosNative().stream()
                .map(row -> new UsuarioMiembrosDTO(
                        (String) row[0],
                        (String) row[1],
                        ((Number) row[2]).longValue()
                ))
                .collect(Collectors.toList());
    }
}