package com.fup.membermanagement.backend_api.model.dto;

public class UsuarioMiembrosDTO {
    private String nombreRole;
    private String username;
    private Long miembrosRegistrados;

    public UsuarioMiembrosDTO(String nombreRole, String username, Long miembrosRegistrados) {
        this.nombreRole = nombreRole;
        this.username = username;
        this.miembrosRegistrados = miembrosRegistrados;
    }

    public String getNombreRole() { return nombreRole; }
    public void setNombreRole(String nombreRole) { this.nombreRole = nombreRole; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public Long getMiembrosRegistrados() { return miembrosRegistrados; }
    public void setMiembrosRegistrados(Long miembrosRegistrados) { this.miembrosRegistrados = miembrosRegistrados; }
}