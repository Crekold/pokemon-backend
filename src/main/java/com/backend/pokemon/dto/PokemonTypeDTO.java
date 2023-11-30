package com.backend.pokemon.dto;

public class PokemonTypeDTO {

    private Long pokemonTypeId;
    private PokemonDTO pokemon;
    private TypeElementDTO typeElement;

    public PokemonTypeDTO() {
    }

    public PokemonTypeDTO(Long pokemonTypeId, PokemonDTO pokemon, TypeElementDTO typeElement) {
        this.pokemonTypeId = pokemonTypeId;
        this.pokemon = pokemon;
        this.typeElement = typeElement;
    }

    public Long getPokemonTypeId() {
        return pokemonTypeId;
    }

    public void setPokemonTypeId(Long pokemonTypeId) {
        this.pokemonTypeId = pokemonTypeId;
    }

    public PokemonDTO getPokemon() {
        return pokemon;
    }

    public void setPokemon(PokemonDTO pokemon) {
        this.pokemon = pokemon;
    }

    public TypeElementDTO getTypeElement() {
        return typeElement;
    }

    public void setTypeElement(TypeElementDTO typeElement) {
        this.typeElement = typeElement;
    }
}
