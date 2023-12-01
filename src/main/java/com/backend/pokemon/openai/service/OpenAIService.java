package com.backend.pokemon.openai.service;

import com.backend.pokemon.openai.dto.OpenAIRequestDTO;
import com.backend.pokemon.openai.dto.OpenAIResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class OpenAIService {

    @Value("${openai.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private static final Logger LOG = LoggerFactory.getLogger(OpenAIService.class);

    @Autowired
    public OpenAIService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public OpenAIResponseDTO createOpenAISuggestion(OpenAIRequestDTO requestDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OpenAIRequestDTO> entity = new HttpEntity<>(requestDTO, headers);
        String openAIEndpoint = "https://api.openai.com/v1/engines/davinci/completions";

        try {
            ResponseEntity<OpenAIResponseDTO> response = restTemplate.postForEntity(openAIEndpoint, entity, OpenAIResponseDTO.class);
            LOG.info("OpenAI suggestion created successfully");
            return response.getBody();
        } catch (HttpClientErrorException e) {
            LOG.error("HttpClientErrorException when calling OpenAI: ", e);
            throw new RuntimeException("Error during OpenAI API call: " + e.getResponseBodyAsString(), e);
        } catch (Exception e) {
            LOG.error("Exception when calling OpenAI: ", e);
            throw new RuntimeException("Error during OpenAI API call: " + e.getMessage(), e);
        }
    }
}