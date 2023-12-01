package com.backend.pokemon.openai.dto;

public class OpenAIRequestDTO {
    private String prompt; // El texto de entrada que el modelo de IA completará.
    private Integer maxTokens; // El número máximo de tokens a generar en la respuesta.
    private Double temperature; // Qué tan conservador o aventurado será el modelo en sus respuestas (0.0 más conservador, 1.0 más aventurado).
    private Integer topP; // Controla la diversidad mediante el muestreo nucleado: 1.0 significa considerar todos los tokens posibles, valores más bajos hacen que el modelo sea más conservador.
    private Integer frequencyPenalty; // Cuanto menor es el valor, más penaliza las nuevas apariciones de tokens ya presentes (desincentiva la repetición).
    private Integer presencePenalty; // Cuanto menor es el valor, más penaliza la presencia de tokens ya presentes (desincentiva la repetición).
    private Integer n; // El número de respuestas independientes a generar para cada solicitud.
    private String engine; // El modelo de IA a utilizar, por ejemplo 'davinci'.

    // Constructor por defecto
    public OpenAIRequestDTO() {
    }

    // Constructor con todos los parámetros
    public OpenAIRequestDTO(String prompt, Integer maxTokens, Double temperature, 
    Integer topP, Integer frequencyPenalty, Integer presencePenalty, Integer n, String engine) {
        this.prompt = prompt;
        this.maxTokens = maxTokens;
        this.temperature = temperature;
        this.topP = topP;
        this.frequencyPenalty = frequencyPenalty;
        this.presencePenalty = presencePenalty;
        this.n = n;
        this.engine = engine;
    }

    // Getters y setters
    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public Integer getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(Integer maxTokens) {
        this.maxTokens = maxTokens;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getTopP() {
        return topP;
    }

    public void setTopP(Integer topP) {
        this.topP = topP;
    }

    public Integer getFrequencyPenalty() {
        return frequencyPenalty;
    }

    public void setFrequencyPenalty(Integer frequencyPenalty) {
        this.frequencyPenalty = frequencyPenalty;
    }

    public Integer getPresencePenalty() {
        return presencePenalty;
    }

    public void setPresencePenalty(Integer presencePenalty) {
        this.presencePenalty = presencePenalty;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "OpenAIRequestDTO{" +
                "prompt='" + prompt + '\'' +
                ", maxTokens=" + maxTokens +
                ", temperature=" + temperature +
                ", topP=" + topP +
                ", frequencyPenalty=" + frequencyPenalty +
                ", presencePenalty=" + presencePenalty +
                ", n=" + n +
                ", engine='" + engine + '\'' +
                '}';
    }
}