package com.fup.membermanagement.backend_api.service;

import com.fup.membermanagement.backend_api.model.dto.AlertaPastoralDTO;
import com.fup.membermanagement.backend_api.model.dto.InformePastoralDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class HuggingFaceService {

    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    @Value("${hf.api.key:}")
    private String hfApiKey;

    @Value("${hf.model.url:https://api-inference.huggingface.co/models/mistralai/Mistral-7B-Instruct-v0.3}")
    private String hfModelUrl;

    public List<AlertaPastoralDTO> generarAlertasAsistencia(Map<String, Object> datosMiembro) {
        List<AlertaPastoralDTO> alertas = new ArrayList<>();
        Integer asistencias = (Integer) datosMiembro.getOrDefault("asistencias_ultimo_mes", 0);
        if (asistencias != null && asistencias < 2) {
            alertas.add(new AlertaPastoralDTO("ASISTENCIA_BAJA",
                    "Miembro con menos de 2 asistencias en el último mes", "ALTA",
                    "Contactar para visita pastoral esta semana"));
        }
        return alertas;
    }

    public InformePastoralDTO generarInformePastoral(Map<String, Object> datosCongregacion) {
        if (hfApiKey.isEmpty() || hfModelUrl.isEmpty()) {
            return generarInformeLocal(datosCongregacion);
        }
        try {
            String prompt = construirPromptMistral(datosCongregacion);
            Map<String, Object> payload = Map.of(
                    "inputs", prompt,
                    "parameters", Map.of("max_new_tokens", 500, "temperature", 0.3, "return_full_text", false)
            );
            String respuesta = restClient.post()
                    .uri(hfModelUrl.replace("https://api-inference.huggingface.co", ""))
                    .header("Authorization", "Bearer " + hfApiKey)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(payload)
                    .retrieve()
                    .body(String.class);
            return parsearInformeMistral(respuesta, datosCongregacion);
        } catch (Exception e) {
            log.warn("Fallback a informe local por error en Mistral: {}", e.getMessage());
            return generarInformeLocal(datosCongregacion);
        }
    }

    private String construirPromptMistral(Map<String, Object> datos) {
        Long totalMiembros = (Long) datos.getOrDefault("total_miembros", 0L);
        Long nuevosEsteMes = (Long) datos.getOrDefault("nuevos_este_mes", 0L);
        Long asistenciaPromedio = (Long) datos.getOrDefault("asistencia_promedio", 0L);
        String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy"));
        return """
            [INST] Eres un asistente pastoral experto en análisis de datos eclesiásticos. 
            Genera un informe ejecutivo breve y accionable para la junta directiva de una iglesia.
            Datos de la congregación:
            - Fecha del informe: %s
            - Total de miembros activos: %d
            - Nuevos miembros este mes: %d
            - Asistencia promedio semanal: %d personas
            Instrucciones:
            1. Escribe en español, tono profesional pero cálido
            2. Máximo 150 palabras
            3. Incluye: tendencia observada, 1 recomendación pastoral concreta
            4. Formato: JSON estricto con claves "resumen", "tendencia", "recomendacion"
            [/INST]
            """.formatted(fecha, totalMiembros, nuevosEsteMes, asistenciaPromedio);
    }

    private InformePastoralDTO parsearInformeMistral(String respuesta, Map<String, Object> datos) {
        try {
            String jsonStr = respuesta;
            if (respuesta.contains("{")) {
                jsonStr = respuesta.substring(respuesta.indexOf("{"));
                if (jsonStr.contains("}")) {
                    jsonStr = jsonStr.substring(0, jsonStr.lastIndexOf("}") + 1);
                }
            }
            var root = objectMapper.readTree(jsonStr);
            InformePastoralDTO informe = new InformePastoralDTO();
            informe.setResumen(root.path("resumen").asText("Sin resumen generado"));
            informe.setTendencia(root.path("tendencia").asText("Sin tendencia identificada"));
            informe.setRecomendacion(root.path("recomendacion").asText("Sin recomendación específica"));
            informe.setFechaGeneracion(LocalDate.now());
            informe.setFuente("Mistral-7B-Instruct-v0.3");
            return informe;
        } catch (Exception e) {
            log.error("Error parseando respuesta de Mistral: {}", e.getMessage());
            return generarInformeLocal(datos);
        }
    }

    private InformePastoralDTO generarInformeLocal(Map<String, Object> datos) {
        Long total = (Long) datos.getOrDefault("total_miembros", 0L);
        Long nuevos = (Long) datos.getOrDefault("nuevos_este_mes", 0L);
        InformePastoralDTO informe = new InformePastoralDTO();
        informe.setResumen("Congregación de " + total + " miembros activos.");
        informe.setTendencia(nuevos > 0 ? "Crecimiento positivo con " + nuevos + " nuevos miembros." : "Estabilidad en membresía.");
        informe.setRecomendacion("Fortalecer grupos de discipulado para retención de nuevos miembros.");
        informe.setFechaGeneracion(LocalDate.now());
        informe.setFuente("Lógica local (fallback)");
        return informe;
    }
}