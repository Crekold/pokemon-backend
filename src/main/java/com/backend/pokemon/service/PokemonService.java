package com.backend.pokemon.service;

import com.backend.pokemon.dto.PokemonDTO;
import com.backend.pokemon.model.Pokemon;
import com.backend.pokemon.repository.PokemonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    private static final Logger LOG = LoggerFactory.getLogger(PokemonService.class);

    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Transactional
    public PokemonDTO savePokemon(PokemonDTO pokemonDTO) {
        LOG.info("Guardando un nuevo Pokemon en la base de datos: {}", pokemonDTO);
        try {
            Pokemon pokemon = convertDTOToEntity(pokemonDTO);
            Pokemon savedPokemon = pokemonRepository.save(pokemon);
            return convertEntityToDTO(savedPokemon);
        } catch (Exception e) {
            LOG.error("Error al guardar un nuevo Pokemon: {}", e.getMessage(), e);
            throw new RuntimeException("Error al guardar un nuevo Pokemon: " + e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    public List<PokemonDTO> findAllPokemons() {
        LOG.info("Recuperando todos los Pokemons de la base de datos");
        try {
            List<Pokemon> pokemons = pokemonRepository.findAll();
            return convertEntityListToDTOList(pokemons);
        } catch (Exception e) {
            LOG.error("Error al recuperar todos los Pokemons: {}", e.getMessage(), e);
            throw new RuntimeException("Error al recuperar todos los Pokemons: " + e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    public PokemonDTO findPokemonById(String id) {
        LOG.info("Recuperando un Pokemon de la base de datos con ID: {}", id);
        try {
            Pokemon pokemon = pokemonRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Pokemon no encontrado con ID: " + id));
            return convertEntityToDTO(pokemon);
        } catch (Exception e) {
            LOG.error("Error al recuperar un Pokemon por ID: {}", e.getMessage(), e);
            throw new RuntimeException("Error al recuperar un Pokemon por ID: " + e.getMessage(), e);
        }
    }

    @Transactional
    public void deletePokemon(String id) {
        LOG.info("Eliminando Pokemon con ID: {}", id);
        try {
            pokemonRepository.deleteById(id);
        } catch (Exception e) {
            LOG.error("Error al eliminar un Pokemon por ID: {}", e.getMessage(), e);
            throw new RuntimeException("Error al eliminar un Pokemon por ID: " + e.getMessage(), e);
        }
    }

    @Transactional
    public PokemonDTO updatePokemon(String id, PokemonDTO newPokemonDTO) {
        LOG.info("Actualizando Pokemon en la base de datos con ID: {}", id);
        try {
            Pokemon newPokemonData = convertDTOToEntity(newPokemonDTO);
            Pokemon updatedPokemon = pokemonRepository.findById(id)
                    .map(existingPokemon -> {
                        existingPokemon.setPokemonName(newPokemonData.getPokemonName());
                        existingPokemon.setImageUrl(newPokemonData.getImageUrl());
                        return pokemonRepository.save(existingPokemon);
                    }).orElseThrow(() -> new RuntimeException("Pokemon no encontrado con ID: " + id));
            return convertEntityToDTO(updatedPokemon);
        } catch (Exception e) {
            LOG.error("Error al actualizar un Pokemon por ID: {}", e.getMessage(), e);
            throw new RuntimeException("Error al actualizar un Pokemon por ID: " + e.getMessage(), e);
        }
    }

    // Métodos auxiliares para la conversión de PokemonDTO a Pokemon y viceversa
    private Pokemon convertDTOToEntity(PokemonDTO pokemonDTO) {
        return new Pokemon(pokemonDTO.getPokemonId(), pokemonDTO.getPokemonName(), pokemonDTO.getImageUrl());
    }

    private PokemonDTO convertEntityToDTO(Pokemon pokemon) {
        return new PokemonDTO(pokemon.getPokemonId(), pokemon.getPokemonName(), pokemon.getImageUrl());
    }

    private List<PokemonDTO> convertEntityListToDTOList(List<Pokemon> pokemons) {
        return pokemons.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }
}
