package com.backend.pokemon.openai.controller;

import com.backend.pokemon.openai.dto.OpenAIRequestDTO;
import com.backend.pokemon.openai.dto.OpenAIResponseDTO;
import com.backend.pokemon.openai.service.OpenAIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/openai")
public class OpenAIController {

    private final OpenAIService openAIService;
    private static final Logger LOG = LoggerFactory.getLogger(OpenAIController.class);

    @Autowired
    public OpenAIController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/suggestions")
    public ResponseEntity<OpenAIResponseDTO> getSuggestion(@RequestBody OpenAIRequestDTO requestDTO) {
        try {
            LOG.info("Creating suggestion with OpenAI");
            OpenAIResponseDTO suggestion = openAIService.createOpenAISuggestion(requestDTO);
            return ResponseEntity.ok(suggestion);
        } catch (Exception e) {
            LOG.error("Error creating suggestion with OpenAI", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
