package com.fup.membermanagement.backend_api.controller;

import com.fup.membermanagement.backend_api.model.dto.UsuarioMiembrosDTO;
import com.fup.membermanagement.backend_api.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/por-rol-con-miembros")
    public ResponseEntity<List<UsuarioMiembrosDTO>> obtenerUsuariosConCargaPastoral() {
        return ResponseEntity.ok(usuarioService.findUsuariosPorRolConMiembros());
    }
}