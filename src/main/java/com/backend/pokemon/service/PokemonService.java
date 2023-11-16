package com.backend.pokemon.service;

import com.backend.pokemon.model.Pokemon;
import com.backend.pokemon.repository.PokemonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PokemonService {

    private static final Logger LOG = LoggerFactory.getLogger(PokemonService.class);

    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Transactional
    public Pokemon savePokemon(Pokemon pokemon) {
        LOG.info("Guardando un nuevo Pokemon en la base de datos: {}", pokemon);
        return pokemonRepository.save(pokemon);
    }

    @Transactional(readOnly = true)
    public List<Pokemon> findAllPokemons() {
        LOG.info("Recuperando todos los Pokemons de la base de datos");
        return pokemonRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Pokemon findPokemonById(String id) {
        LOG.info("Recuperando un Pokemon de la base de datos con ID: {}", id);
        return pokemonRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pokemon no encontrado con ID: " + id));
    }

    @Transactional
    public void deletePokemon(String id) {
        LOG.info("Eliminando Pokemon con ID: {}", id);
        pokemonRepository.deleteById(id);
    }

    @Transactional
    public Pokemon updatePokemon(String id, Pokemon newPokemonData) {
        LOG.info("Actualizando Pokemon en la base de datos con ID: {}", id);
        return pokemonRepository.findById(id)
            .map(existingPokemon -> {
                existingPokemon.setPokemonName(newPokemonData.getPokemonName());
                existingPokemon.setImageUrl(newPokemonData.getImageUrl());
                return pokemonRepository.save(existingPokemon);
            }).orElseThrow(() -> new RuntimeException("Pokemon no encontrado con ID: " + id));
    }
}
