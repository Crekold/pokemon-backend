package com.backend.pokemon.dto;

public class PokemonDTO {

    private String pokemonId;
    private String pokemonName;
    private String imageUrl;

    // Constructores, getters y setters

    public PokemonDTO() {
    }

    public PokemonDTO(String pokemonId, String pokemonName, String imageUrl) {
        this.pokemonId = pokemonId;
        this.pokemonName = pokemonName;
        this.imageUrl = imageUrl;
    }

    public String getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(String pokemonId) {
        this.pokemonId = pokemonId;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
