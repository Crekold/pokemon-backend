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

    @ManyToOne()
    @JoinColumn(name = "type_element_type_element_id")
    private TypeElement typeElement;

    // Constructores, getters y setters
    public Pokemon() {
    }

    public Pokemon(String pokemonId, String pokemonName, TypeElement typeElement) {
        this.pokemonId = pokemonId;
        this.pokemonName = pokemonName;
        this.typeElement = typeElement;
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

    public TypeElement getTypeElement() {
        return typeElement;
    }

    public void setTypeElement(TypeElement typeElement) {
        this.typeElement = typeElement;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "pokemonId='" + pokemonId + '\'' +
                ", pokemonName='" + pokemonName + '\'' +
                ", typeElement=" + (typeElement != null ? typeElement.getTypeElementName() : "null") +
                '}';
    }
}
