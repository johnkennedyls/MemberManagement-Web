package com.fup.membermanagement.backend_api.model.dto;

import java.time.LocalDate;

public class InformePastoralDTO {
    private String resumen;
    private String tendencia;
    private String recomendacion;
    private LocalDate fechaGeneracion;
    private String fuente;

    public String getResumen() { return resumen; }
    public void setResumen(String resumen) { this.resumen = resumen; }
    public String getTendencia() { return tendencia; }
    public void setTendencia(String tendencia) { this.tendencia = tendencia; }
    public String getRecomendacion() { return recomendacion; }
    public void setRecomendacion(String recomendacion) { this.recomendacion = recomendacion; }
    public LocalDate getFechaGeneracion() { return fechaGeneracion; }
    public void setFechaGeneracion(LocalDate fechaGeneracion) { this.fechaGeneracion = fechaGeneracion; }
    public String getFuente() { return fuente; }
    public void setFuente(String fuente) { this.fuente = fuente; }
}