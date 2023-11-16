package com.backend.pokemon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.backend.pokemon.model.PokemonType;
import com.backend.pokemon.service.PokemonTypeService;

import java.util.List;

@RestController
@RequestMapping("/pokemonType")
public class PokemonTypeController {

    @Autowired
    private PokemonTypeService pokemonTypeService;

    @PostMapping
    public PokemonType createPokemonType(@RequestBody PokemonType pokemonType) {
        return pokemonTypeService.savePokemonType(pokemonType);
    }

    @GetMapping
    public List<PokemonType> getAllPokemonTypes() {
        return pokemonTypeService.getAllPokemonTypes();
    }

    @GetMapping("/{id}")
    public PokemonType getPokemonTypeById(@PathVariable Long id) {
        return pokemonTypeService.getPokemonTypeById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletePokemonType(@PathVariable Long id) {
        pokemonTypeService.deletePokemonType(id);
    }

    // Puedes agregar aquí endpoints adicionales según sea necesario
}

