package com.fup.membermanagement.backend_api.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "AUDIT_LOGS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LOG")
    private Long idLog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO")
    private User usuario;

    @Column(name = "ACCION", nullable = false, length = 50)
    private String accion;

    @Column(name = "TABLA_AFECTADA", length = 50)
    private String tablaAfectada;

    @Column(name = "REGISTRO_ID")
    private Long registroId;

    @Column(name = "DETALLE", columnDefinition = "CLOB")
    private String detalle;

    @Column(name = "FECHA_HORA")
    private LocalDateTime fechaHora;

    @Column(name = "TIPO_ACCION", length = 30)
    @Enumerated(EnumType.STRING)
    private TipoAccion tipoAccion;

    public enum TipoAccion {
        ADMINISTRATIVO, PASTORAL, ASISTENCIA, SINCRONIZACION
    }
}