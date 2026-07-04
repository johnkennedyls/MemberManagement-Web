package com.fup.membermanagement.backend_api.model.dto;

public class AlertaPastoralDTO {
    private String tipo;
    private String mensaje;
    private String prioridad;
    private String accionRecomendada;

    public AlertaPastoralDTO(String tipo, String mensaje, String prioridad, String accionRecomendada) {
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.prioridad = prioridad;
        this.accionRecomendada = accionRecomendada;
    }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public String getPrioridad() { return prioridad; }
    public void setPrioridad(String prioridad) { this.prioridad = prioridad; }
    public String getAccionRecomendada() { return accionRecomendada; }
    public void setAccionRecomendada(String accionRecomendada) { this.accionRecomendada = accionRecomendada; }
}