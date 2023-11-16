package com.backend.pokemon.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.pokemon.model.PokemonType;
import com.backend.pokemon.repository.PokemonTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonTypeService {

    @Autowired
    private PokemonTypeRepository pokemonTypeRepository;

    public PokemonType savePokemonType(PokemonType pokemonType) {
        return pokemonTypeRepository.save(pokemonType);
    }

    public List<PokemonType> getAllPokemonTypes() {
        return pokemonTypeRepository.findAll();
    }

    public Optional<PokemonType> getPokemonTypeById(Long id) {
        return pokemonTypeRepository.findById(id);
    }

    public void deletePokemonType(Long id) {
        pokemonTypeRepository.deleteById(id);
    }

    // Puedes agregar aquí métodos adicionales según sea necesario
}
