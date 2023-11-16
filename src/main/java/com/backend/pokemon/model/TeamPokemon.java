package com.backend.pokemon.model;

import jakarta.persistence.*;

@Entity
@Table(name = "team_pokemon")
public class TeamPokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_pokemon_id")
    private Long teamPokemonId;
    
    @ManyToOne()
    @JoinColumn(name = "pokemon_pokemon_id")
    private Pokemon pokemon;

    @ManyToOne()
    @JoinColumn(name = "team_team_id")
    private Team team;

    // Constructores, getters y setters
    public TeamPokemon() {
    }

    public TeamPokemon(Pokemon pokemon, Team team) {
        this.pokemon = pokemon;
        this.team = team;
    }

    public Long getTeamPokemonId() {
        return teamPokemonId;
    }

    public void setTeamPokemonId(Long teamPokemonId) {
        this.teamPokemonId = teamPokemonId;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
