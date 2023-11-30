package com.backend.pokemon.dto;

public class TeamPokemonDTO {

    private Long teamPokemonId;
    private PokemonDTO pokemon;
    private TeamDTO team;

    public TeamPokemonDTO() {
    }

    public TeamPokemonDTO(Long teamPokemonId, PokemonDTO pokemon, TeamDTO team) {
        this.teamPokemonId = teamPokemonId;
        this.pokemon = pokemon;
        this.team = team;
    }

    public Long getTeamPokemonId() {
        return teamPokemonId;
    }

    public void setTeamPokemonId(Long teamPokemonId) {
        this.teamPokemonId = teamPokemonId;
    }

    public PokemonDTO getPokemon() {
        return pokemon;
    }

    public void setPokemon(PokemonDTO pokemon) {
        this.pokemon = pokemon;
    }

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }
}
