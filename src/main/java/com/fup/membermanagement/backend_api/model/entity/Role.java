package com.fup.membermanagement.backend_api.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ROLES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ROLE")
    private Long idRole;

    @Column(name = "NOMBRE_ROLE", nullable = false, unique = true, length = 50)
    private String nombreRole;

    @Column(name = "DESCRIPCION", length = 255)
    private String descripcion;
}