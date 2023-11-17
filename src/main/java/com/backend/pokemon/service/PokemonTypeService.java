package com.backend.pokemon.service;

import com.backend.pokemon.model.PokemonType;
import com.backend.pokemon.repository.PokemonTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonTypeService {

    private static final Logger LOG = LoggerFactory.getLogger(PokemonTypeService.class);

    private final PokemonTypeRepository pokemonTypeRepository;

    @Autowired
    public PokemonTypeService(PokemonTypeRepository pokemonTypeRepository) {
        this.pokemonTypeRepository = pokemonTypeRepository;
    }

    public PokemonType savePokemonType(PokemonType pokemonType) {
        LOG.info("Guardando PokemonType: {}", pokemonType);
        return pokemonTypeRepository.save(pokemonType);
    }

    public List<PokemonType> findAllPokemonTypes() {
        LOG.info("Obteniendo todos los PokemonTypes");
        return pokemonTypeRepository.findAll();
    }

    public PokemonType findPokemonTypeById(Long id) {
        LOG.info("Buscando PokemonType con ID: {}", id);
        return pokemonTypeRepository.findById(id).orElseThrow(() ->
                new RuntimeException("PokemonType no encontrado con ID: " + id));
    }

    public void deletePokemonType(Long id) {
        LOG.info("Eliminando PokemonType con ID: {}", id);
        pokemonTypeRepository.deleteById(id);
    }

    public PokemonType updatePokemonType(Long id, PokemonType newPokemonTypeData) {
        LOG.info("Actualizando PokemonType con ID: {}", id);
        return pokemonTypeRepository.findById(id)
                .map(existingPokemonType -> {
                    existingPokemonType.setPokemon(newPokemonTypeData.getPokemon());
                    existingPokemonType.setTypeElement(newPokemonTypeData.getTypeElement());
                    return pokemonTypeRepository.save(existingPokemonType);
                }).orElseThrow(() -> new RuntimeException("PokemonType no encontrado con ID: " + id));
    }
}
