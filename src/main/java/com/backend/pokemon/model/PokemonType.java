package com.backend.pokemon.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pokemon_type")
public class PokemonType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pokemon_type_id")
    private Long pokemonTypeId;

    @ManyToOne
    @JoinColumn(name = "pokemon_id")
    private Pokemon pokemon;

    @ManyToOne
    @JoinColumn(name = "type_element_id")
    private TypeElement typeElement;

    // Constructores
    public PokemonType() {
    }

    public PokemonType(Pokemon pokemon, TypeElement typeElement) {
        this.pokemon = pokemon;
        this.typeElement = typeElement;
    }

    // Getters y setters
    public Long getPokemonTypeId() {
        return pokemonTypeId;
    }

    public void setPokemonTypeId(Long pokemonTypeId) {
        this.pokemonTypeId = pokemonTypeId;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public TypeElement getTypeElement() {
        return typeElement;
    }

    public void setTypeElement(TypeElement typeElement) {
        this.typeElement = typeElement;
    }

    // toString()
    @Override
    public String toString() {
        return "PokemonType{" +
                "pokemonTypeId=" + pokemonTypeId +
                ", pokemon=" + (pokemon != null ? pokemon.getPokemonId() : "null") +
                ", typeElement=" + (typeElement != null ? typeElement.getTypeElementId() : "null") +
                '}';
    }
}
