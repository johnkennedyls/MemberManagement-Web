package com.fup.membermanagement.backend_api.controller;

import com.fup.membermanagement.backend_api.model.dto.MiembroDTO;
import com.fup.membermanagement.backend_api.model.entity.Miembro;
import com.fup.membermanagement.backend_api.service.HuggingFaceService;
import com.fup.membermanagement.backend_api.service.MiembroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class UiController {

    private final MiembroService miembroService;
    private final HuggingFaceService huggingFaceService;

    @GetMapping
    public String dashboard(Model model, RedirectAttributes redirectAttributes) {
        String mensaje = redirectAttributes.getFlashAttributes().isEmpty() ? null :
                redirectAttributes.getFlashAttributes().get("mensaje").toString();
        model.addAttribute("mensajeExito", mensaje);
        model.addAttribute("resumen", miembroService.findResumenDashboard());
        model.addAttribute("miembrosRecientes", miembroService.findNuevosMiembrosUltimoMes(LocalDate.now().minusDays(30)));
        Map<String, Object> datosIA = miembroService.obtenerDatosParaIA();
        model.addAttribute("informeIA", huggingFaceService.generarInformePastoral(datosIA));
        model.addAttribute("alertas", huggingFaceService.generarAlertasAsistencia(datosIA));
        return "dashboard";
    }

    @GetMapping("/miembros")
    public String listarMiembros(Model model) {
        model.addAttribute("miembros", miembroService.findMiembrosActivosConRegistrador());
        return "miembros/list";
    }

    @GetMapping("/miembros/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("miembro", new MiembroDTO());
        return "miembros/form";
    }

    @PostMapping("/miembros/guardar")
    public String guardarMiembro(@ModelAttribute MiembroDTO dto, RedirectAttributes redirectAttributes) {
        try {
            Miembro entidad = new Miembro();
            entidad.setNumCedula(dto.getNumCedula());
            entidad.setNombres(dto.getNombres());
            entidad.setApellidos(dto.getApellidos());

            // ✅ CORRECCIÓN: Convertir String (del formulario) a Enum (de la entidad)
            if (dto.getGenero() != null && !dto.getGenero().isEmpty()) {
                entidad.setGenero(Miembro.Genero.valueOf(dto.getGenero()));
            }

            if (dto.getEstado() != null && !dto.getEstado().isEmpty()) {
                entidad.setEstado(Miembro.MemberEstado.valueOf(dto.getEstado()));
            } else {
                entidad.setEstado(Miembro.MemberEstado.ACTIVO);
            }

            if (dto.getEstadoEspiritual() != null && !dto.getEstadoEspiritual().isEmpty()) {
                // Aquí estaba el error: se debe convertir el String a Enum usando valueOf
                entidad.setEstadoEspiritual(Miembro.EstadoEspiritual.valueOf(dto.getEstadoEspiritual()));
            }

            entidad.setTelefono(dto.getTelefono());
            entidad.setCorreo(dto.getCorreo());
            entidad.setGrupoCelula(dto.getGrupoCelula());
            entidad.setDireccion(dto.getDireccion());
            entidad.setLugarExpedicion(dto.getLugarExpedicion());
            entidad.setVigenciaCedula(dto.getVigenciaCedula());
            entidad.setFechaNacimiento(dto.getFechaNacimiento());
            entidad.setFechaBautismo(dto.getFechaBautismo());

            miembroService.createMiembro(entidad);
            redirectAttributes.addFlashAttribute("mensaje", "Miembro registrado exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al registrar: " + e.getMessage());
        }
        return "redirect:/";
    }
}