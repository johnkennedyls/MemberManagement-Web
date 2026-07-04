package com.fup.membermanagement.backend_api.model.dto;

import java.time.LocalDateTime;

public class SincronizacionDTO {
    private Long idMiembro;
    private String numCedula;
    private String nombres;
    private String estadoSync;
    private Integer versionRegistro;
    private LocalDateTime ultimaActualizacion;

    public SincronizacionDTO(Long idMiembro, String numCedula, String nombres, String estadoSync, Integer versionRegistro, LocalDateTime ultimaActualizacion) {
        this.idMiembro = idMiembro;
        this.numCedula = numCedula;
        this.nombres = nombres;
        this.estadoSync = estadoSync;
        this.versionRegistro = versionRegistro;
        this.ultimaActualizacion = ultimaActualizacion;
    }

    public Long getIdMiembro() { return idMiembro; }
    public void setIdMiembro(Long idMiembro) { this.idMiembro = idMiembro; }
    public String getNumCedula() { return numCedula; }
    public void setNumCedula(String numCedula) { this.numCedula = numCedula; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getEstadoSync() { return estadoSync; }
    public void setEstadoSync(String estadoSync) { this.estadoSync = estadoSync; }
    public Integer getVersionRegistro() { return versionRegistro; }
    public void setVersionRegistro(Integer versionRegistro) { this.versionRegistro = versionRegistro; }
    public LocalDateTime getUltimaActualizacion() { return ultimaActualizacion; }
    public void setUltimaActualizacion(LocalDateTime ultimaActualizacion) { this.ultimaActualizacion = ultimaActualizacion; }
}