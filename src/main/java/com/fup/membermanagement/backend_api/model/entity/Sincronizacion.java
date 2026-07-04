package com.fup.membermanagement.backend_api.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SINCRONIZACION")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sincronizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SYNC")
    private Long idSync;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MIEMBRO", nullable = false)
    private Miembro miembro;

    @Column(name = "ULTIMA_ACTUALIZACION")
    private LocalDateTime ultimaActualizacion;

    @Column(name = "ESTADO_SYNC", length = 20)
    @Enumerated(EnumType.STRING)
    private SyncEstado estadoSync;

    @Column(name = "VERSION_REGISTRO")
    private Integer versionRegistro;

    public enum SyncEstado {
        SINCRONIZADO, PENDIENTE, CONFLICTO
    }
}