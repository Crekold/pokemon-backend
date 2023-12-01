package com.backend.pokemon.openai.dto;

public class OpenAIRequestDTO {
    private String prompt;
    private Double temperature;
    private Integer maxTokens;

    // Constructor

    public OpenAIRequestDTO(String prompt, Double temperature, Integer maxTokens) {
        this.prompt = prompt;
        this.temperature = temperature;
        this.maxTokens = maxTokens;
    }

    public OpenAIRequestDTO() {
    }

    // Getters y setters

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(Integer maxTokens) {
        this.maxTokens = maxTokens;
    }


    // toString

    @Override
    public String toString() {
        return "OpenAIRequestDTO{" +
                "prompt='" + prompt + '\'' +
                ", temperature=" + temperature +
                ", maxTokens=" + maxTokens +
                '}';
    }
}
