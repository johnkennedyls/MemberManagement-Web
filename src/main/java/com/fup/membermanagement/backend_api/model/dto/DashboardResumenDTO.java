package com.fup.membermanagement.backend_api.model.dto;

public class DashboardResumenDTO {
    private Long totalMiembrosActivos;
    private Long nuevosEsteMes;
    private Long usuariosActivos;

    public DashboardResumenDTO(Long totalMiembrosActivos, Long nuevosEsteMes, Long usuariosActivos) {
        this.totalMiembrosActivos = totalMiembrosActivos;
        this.nuevosEsteMes = nuevosEsteMes;
        this.usuariosActivos = usuariosActivos;
    }

    public Long getTotalMiembrosActivos() { return totalMiembrosActivos; }
    public void setTotalMiembrosActivos(Long totalMiembrosActivos) { this.totalMiembrosActivos = totalMiembrosActivos; }
    public Long getNuevosEsteMes() { return nuevosEsteMes; }
    public void setNuevosEsteMes(Long nuevosEsteMes) { this.nuevosEsteMes = nuevosEsteMes; }
    public Long getUsuariosActivos() { return usuariosActivos; }
    public void setUsuariosActivos(Long usuariosActivos) { this.usuariosActivos = usuariosActivos; }
}