package com.backend.pokemon.dto;

import java.util.List;

public class CreateTeamRequestDTO {
    private Long teamId;
    private String teamName;
    private String userId;
    private List<String> pokemonIds;

    // Constructor, getters y setters

    public CreateTeamRequestDTO() {
    }

    public CreateTeamRequestDTO(Long teamId, String teamName, String userId, List<String> pokemonIds) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.userId = userId;
        this.pokemonIds = pokemonIds;
    }

    // Getters y setters

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getPokemonIds() {
        return pokemonIds;
    }

    public void setPokemonIds(List<String> pokemonIds) {
        this.pokemonIds = pokemonIds;
    }

}
