package com.backend.pokemon.dto;

import java.util.List;

public class CreateTeamRequestDTO {
    private String teamName;
    private String userId;
    private List<String> pokemonIds;

    // Constructor, getters y setters

    public CreateTeamRequestDTO() {
    }

    public CreateTeamRequestDTO(String teamName, String userId, List<String> pokemonIds) {
        this.teamName = teamName;
        this.userId = userId;
        this.pokemonIds = pokemonIds;
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
