package com.backend.pokemon.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pokemon_stats")
public class PokemonStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stats")
    private Long idStats;

    @Column(name = "hp", nullable = false)
    private int hp;

    @Column(name = "attack", nullable = false)
    private int attack;

    @Column(name = "defense", nullable = false)
    private int defense;

    @Column(name = "special_attack", nullable = false)
    private int specialAttack;

    @Column(name = "special_defense", nullable = false)
    private int specialDefense;

    @ManyToOne
    @JoinColumn(name = "pokemon_pokemon_id")
    private Pokemon pokemon;

    // Constructores, getters y setters

    public PokemonStats() {
    }

    public PokemonStats(int hp, int attack, int defense, int specialAttack, int specialDefense, Pokemon pokemon) {
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.pokemon = pokemon;
    }

    public Long getIdStats() {
        return idStats;
    }

    public void setIdStats(Long idStats) {
        this.idStats = idStats;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(int specialAttack) {
        this.specialAttack = specialAttack;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(int specialDefense) {
        this.specialDefense = specialDefense;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
}
