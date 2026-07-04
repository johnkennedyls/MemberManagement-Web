package com.fup.membermanagement.backend_api.model.dto;

public class MiembroRegistradorDTO {
    private Long idMiembro;
    private String numCedula;
    private String nombreCompleto;
    private String estado;
    private String registradoPor;

    public MiembroRegistradorDTO(Long idMiembro, String numCedula, String nombres, String apellidos, String estado, String registradoPor) {
        this.idMiembro = idMiembro;
        this.numCedula = numCedula;
        this.nombreCompleto = (nombres != null ? nombres : "") + " " + (apellidos != null ? apellidos : "");
        this.estado = estado;  // String directo, sin conversión de Enum
        this.registradoPor = registradoPor;
    }

    // Getters
    public Long getIdMiembro() { return idMiembro; }
    public String getNumCedula() { return numCedula; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getEstado() { return estado; }
    public String getRegistradoPor() { return registradoPor; }
}