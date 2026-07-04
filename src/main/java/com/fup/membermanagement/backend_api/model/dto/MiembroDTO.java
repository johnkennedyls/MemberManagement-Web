package com.fup.membermanagement.backend_api.model.dto;

import com.fup.membermanagement.backend_api.model.entity.Miembro;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MiembroDTO {
    private Long idMiembro;
    private String numCedula;
    private String nombres;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String lugarExpedicion;
    private String genero;
    private String direccion;
    private LocalDate vigenciaCedula;
    private String estado;
    private String estadoEspiritual;
    private String telefono;
    private String correo;
    private String grupoCelula;
    private LocalDate fechaBautismo;
    private LocalDateTime fechaRegistro;

    public static MiembroDTO fromEntity(Miembro miembro) {
        if (miembro == null) return null;
        MiembroDTO dto = new MiembroDTO();
        dto.setIdMiembro(miembro.getIdMiembro());
        dto.setNumCedula(miembro.getNumCedula());
        dto.setNombres(miembro.getNombres());
        dto.setApellidos(miembro.getApellidos());
        dto.setFechaNacimiento(miembro.getFechaNacimiento());
        dto.setLugarExpedicion(miembro.getLugarExpedicion());
        dto.setGenero(miembro.getGenero() != null ? miembro.getGenero().name() : null);
        dto.setDireccion(miembro.getDireccion());
        dto.setVigenciaCedula(miembro.getVigenciaCedula());
        dto.setEstado(miembro.getEstado() != null ? miembro.getEstado().name() : null);
        dto.setEstadoEspiritual(miembro.getEstadoEspiritual() != null ? miembro.getEstadoEspiritual().name() : null);
        dto.setTelefono(miembro.getTelefono());
        dto.setCorreo(miembro.getCorreo());
        dto.setGrupoCelula(miembro.getGrupoCelula());
        dto.setFechaBautismo(miembro.getFechaBautismo());
        dto.setFechaRegistro(miembro.getFechaRegistro());
        return dto;
    }

    public Long getIdMiembro() { return idMiembro; }
    public void setIdMiembro(Long idMiembro) { this.idMiembro = idMiembro; }
    public String getNumCedula() { return numCedula; }
    public void setNumCedula(String numCedula) { this.numCedula = numCedula; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public String getLugarExpedicion() { return lugarExpedicion; }
    public void setLugarExpedicion(String lugarExpedicion) { this.lugarExpedicion = lugarExpedicion; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public LocalDate getVigenciaCedula() { return vigenciaCedula; }
    public void setVigenciaCedula(LocalDate vigenciaCedula) { this.vigenciaCedula = vigenciaCedula; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getEstadoEspiritual() { return estadoEspiritual; }
    public void setEstadoEspiritual(String estadoEspiritual) { this.estadoEspiritual = estadoEspiritual; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getGrupoCelula() { return grupoCelula; }
    public void setGrupoCelula(String grupoCelula) { this.grupoCelula = grupoCelula; }
    public LocalDate getFechaBautismo() { return fechaBautismo; }
    public void setFechaBautismo(LocalDate fechaBautismo) { this.fechaBautismo = fechaBautismo; }
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}