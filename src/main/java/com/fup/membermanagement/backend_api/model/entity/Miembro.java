package com.fup.membermanagement.backend_api.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "MIEMBROS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Miembro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MIEMBRO")
    private Long idMiembro;

    @NotNull
    @Size(max = 20)
    @Column(name = "NUM_CEDULA", nullable = false, unique = true, length = 20)
    private String numCedula;

    @NotNull
    @Size(max = 100)
    @Column(name = "NOMBRES", nullable = false, length = 100)
    private String nombres;

    @NotNull
    @Size(max = 100)
    @Column(name = "APELLIDOS", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "FECHA_NACIMIENTO")
    private LocalDate fechaNacimiento;

    @Size(max = 100)
    @Column(name = "LUGAR_EXPEDICION", length = 100)
    private String lugarExpedicion;

    @Column(name = "GENERO", length = 15)
    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Size(max = 255)
    @Column(name = "DIRECCION", length = 255)
    private String direccion;

    @Column(name = "VIGENCIA_CEDULA")
    private LocalDate vigenciaCedula;

    @Column(name = "ESTADO", length = 20)
    @Enumerated(EnumType.STRING)
    private MemberEstado estado;

    @Column(name = "FECHA_REGISTRO")
    private LocalDateTime fechaRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO_REGISTRA")
    private User usuarioRegistra;

    @Column(name = "FECHA_BAUTISMO")
    private LocalDate fechaBautismo;

    @Column(name = "ESTADO_ESPIRITUAL", length = 30)
    @Enumerated(EnumType.STRING)
    private EstadoEspiritual estadoEspiritual;

    @Size(max = 20)
    @Column(name = "TELEFONO", length = 20)
    private String telefono;

    @Size(max = 100)
    @Column(name = "CORREO", length = 100)
    private String correo;

    @Size(max = 100)
    @Column(name = "GRUPO_CELULA", length = 100)
    private String grupoCelula;

    public String getNombreCompleto() {
        return (nombres != null ? nombres : "") + " " + (apellidos != null ? apellidos : "");
    }

    public enum Genero { MASCULINO, FEMENINO, OTRO, NO_ESPECIFICADO }
    public enum MemberEstado { ACTIVO, INACTIVO, ELIMINADO }
    public enum EstadoEspiritual { BAUTIZADO, EN_PROCESO, VISITA, MIEMBRO_ACTIVO, INACTIVO }
}