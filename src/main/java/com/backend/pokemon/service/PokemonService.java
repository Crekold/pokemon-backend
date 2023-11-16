package com.backend.pokemon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.pokemon.model.Pokemon;
import com.backend.pokemon.repository.PokemonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    public Pokemon savePokemon(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    public List<Pokemon> getAllPokemon() {
        return pokemonRepository.findAll();
    }

    public Optional<Pokemon> getPokemonById(String id) {
        return pokemonRepository.findById(id);
    }

    public void deletePokemon(String id) {
        pokemonRepository.deleteById(id);
    }

    // Método para actualizar un Pokémon existente
}
