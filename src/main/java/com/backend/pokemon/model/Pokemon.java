package com.backend.pokemon.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pokemon")
public class Pokemon {

    @Id
    @Column(name = "pokemon_id")
    private String pokemonId;

    @Column(name = "pokemon_name")
    private String pokemonName;

    @Column(name = "image_url")
    private String imageUrl;

    // Constructores, getters y setters
    public Pokemon() {
    }

    public Pokemon(String pokemonId, String pokemonName, String imageUrl) {
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

    @Override
    public String toString() {
        return "Pokemon{" +
                "pokemonId='" + pokemonId + '\'' +
                ", pokemonName='" + pokemonName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
