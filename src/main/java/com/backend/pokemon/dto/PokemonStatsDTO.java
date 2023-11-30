package com.backend.pokemon.dto;

public class PokemonStatsDTO {

    private Long idStats;
    private int hp;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;
    private PokemonDTO pokemon;

    // Constructores, getters y setters

    public PokemonStatsDTO() {
    }

    public PokemonStatsDTO(Long idStats, int hp, int attack, int defense, int specialAttack, int specialDefense, PokemonDTO pokemon) {
        this.idStats = idStats;
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

    public PokemonDTO getPokemon() {
        return pokemon;
    }

    public void setPokemon(PokemonDTO pokemon) {
        this.pokemon = pokemon;
    }

}
