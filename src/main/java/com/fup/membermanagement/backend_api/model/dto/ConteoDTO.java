package com.fup.membermanagement.backend_api.model.dto;

public class ConteoDTO {
    private String categoria1;
    private String categoria2;
    private Long total;

    public ConteoDTO(String categoria1, String categoria2, Long total) {
        this.categoria1 = categoria1;
        this.categoria2 = categoria2;
        this.total = total;
    }

    public String getCategoria1() { return categoria1; }
    public void setCategoria1(String categoria1) { this.categoria1 = categoria1; }
    public String getCategoria2() { return categoria2; }
    public void setCategoria2(String categoria2) { this.categoria2 = categoria2; }
    public Long getTotal() { return total; }
    public void setTotal(Long total) { this.total = total; }
}