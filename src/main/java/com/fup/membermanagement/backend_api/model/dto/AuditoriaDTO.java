package com.fup.membermanagement.backend_api.model.dto;

import java.time.LocalDateTime;

public class AuditoriaDTO {
    private Long idLog;
    private LocalDateTime fechaHora;
    private String username;
    private String accion;
    private String detalle;
    private String numCedula;

    public AuditoriaDTO(Long idLog, LocalDateTime fechaHora, String username, String accion, String detalle, String numCedula) {
        this.idLog = idLog;
        this.fechaHora = fechaHora;
        this.username = username;
        this.accion = accion;
        this.detalle = detalle;
        this.numCedula = numCedula;
    }

    public Long getIdLog() { return idLog; }
    public void setIdLog(Long idLog) { this.idLog = idLog; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getAccion() { return accion; }
    public void setAccion(String accion) { this.accion = accion; }
    public String getDetalle() { return detalle; }
    public void setDetalle(String detalle) { this.detalle = detalle; }
    public String getNumCedula() { return numCedula; }
    public void setNumCedula(String numCedula) { this.numCedula = numCedula; }
}