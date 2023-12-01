package com.backend.pokemon.openai.service;

import com.backend.pokemon.openai.dto.OpenAIRequestDTO;
import com.backend.pokemon.openai.dto.OpenAIResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenAIService {

    private static final Logger log = LoggerFactory.getLogger(OpenAIService.class);

    private final RestTemplate restTemplate;

    @Value("${openai.api.url}")
    private String openaiApiUrl;

    public OpenAIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OpenAIResponseDTO callOpenAI(OpenAIRequestDTO requestDTO) {
        log.info("Llamando a la API de OpenAI con el siguiente cuerpo: {}", requestDTO.toString());

        // Establecer el modelo por defecto si no se ha proporcionado
        if (requestDTO.getEngine() == null || requestDTO.getEngine().isEmpty()) {
            requestDTO.setEngine("davinci");
            log.info("No se proporcionó un modelo. Utilizando el modelo por defecto: {}", requestDTO.getEngine());
        }

        String endpoint = "/completions"; // Cambiar según el endpoint deseado
        try {
            return restTemplate.postForObject(openaiApiUrl + endpoint, requestDTO, OpenAIResponseDTO.class);
        } catch (RestClientException e) {
            log.error("Error al llamar a la API de OpenAI: {}", e.getMessage());
            // Aquí podrías lanzar una excepción personalizada o devolver una respuesta vacía/null
            return null;
        }
    }
}
