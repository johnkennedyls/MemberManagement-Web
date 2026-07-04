package com.fup.membermanagement.backend_api.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "USUARIOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long idUsuario;

    @NotNull
    @Column(name = "USERNAME", nullable = false, unique = true, length = 50)
    private String username;

    @NotNull
    @Column(name = "PASSWORD_HASH", nullable = false, length = 255)
    private String passwordHash;

    @NotNull
    @Email
    @Column(name = "EMAIL", nullable = false, unique = true, length = 100)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ROLE", nullable = false)
    private Role role;

    @Column(name = "ESTADO", length = 20)
    @Enumerated(EnumType.STRING)
    private UserEstado estado;

    @Column(name = "FECHA_CREACION")
    private LocalDateTime fechaCreacion;
}