package com.backend.pokemon.openai.dto;

import java.util.List;

public class OpenAIResponseDTO {
    private String id;
    private List<Choice> choices;

    // Constructor vacío
    public OpenAIResponseDTO() {}

    // Constructor con todos los campos
    public OpenAIResponseDTO(String id, List<Choice> choices) {
        this.id = id;
        this.choices = choices;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public static class Choice {
        private String text;
        private Integer index;
        private Object logprobs; // Asume un tipo genérico, ajusta según necesidad
        private String finishReason;

        // Constructor vacío
        public Choice() {}

        // Constructor con todos los campos
        public Choice(String text, Integer index, Object logprobs, String finishReason) {
            this.text = text;
            this.index = index;
            this.logprobs = logprobs;
            this.finishReason = finishReason;
        }

        // Getters y Setters
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public Object getLogprobs() {
            return logprobs;
        }

        public void setLogprobs(Object logprobs) {
            this.logprobs = logprobs;
        }

        public String getFinishReason() {
            return finishReason;
        }

        public void setFinishReason(String finishReason) {
            this.finishReason = finishReason;
        }
    }
}
