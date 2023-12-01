package com.backend.pokemon.openai.dto;

import java.util.List;

public class OpenAIResponseDTO {
    private List<String> choices; // Suponiendo que queremos capturar solo las opciones de respuesta.

    // Constructor por defecto
    public OpenAIResponseDTO() {
    }

    // Getters y setters
    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }
}
