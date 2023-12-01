package com.backend.pokemon.openai.controller;

import com.backend.pokemon.openai.dto.OpenAIRequestDTO;
import com.backend.pokemon.openai.dto.OpenAIResponseDTO;
import com.backend.pokemon.openai.service.OpenAIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/openai")
public class OpenAIController {

    private static final Logger log = LoggerFactory.getLogger(OpenAIController.class);

    private final OpenAIService openAIService;

    public OpenAIController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/completions")
    public ResponseEntity<?> createCompletion(@RequestBody OpenAIRequestDTO requestDTO) {
        try {
            OpenAIResponseDTO responseDTO = openAIService.callOpenAI(requestDTO);
            if (responseDTO != null) {
                return ResponseEntity.ok(responseDTO);
            } else {
                log.error("La respuesta de la API de OpenAI fue nula");
                return ResponseEntity.badRequest().body("La solicitud no pudo ser procesada por la API de OpenAI.");
            }
        } catch (Exception e) {
            log.error("Excepción al procesar la solicitud: {}", e.getMessage());
            return ResponseEntity.internalServerError().body("Error interno del servidor: " + e.getMessage());
        }
    }
}
