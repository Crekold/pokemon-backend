package com.backend.pokemon.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.backend.pokemon.model.Pokemon;
import com.backend.pokemon.service.PokemonService;

import java.util.List;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @PostMapping
    public Pokemon createPokemon(@RequestBody Pokemon pokemon) {
        return pokemonService.savePokemon(pokemon);
    }

    @GetMapping
    public List<Pokemon> getAllPokemon() {
        return pokemonService.getAllPokemon();
    }

    @GetMapping("/{id}")
    public Pokemon getPokemonById(@PathVariable String id) {
        return pokemonService.getPokemonById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletePokemon(@PathVariable String id) {
        pokemonService.deletePokemon(id);
    }

    // Endpoint para actualizar un Pokémon
}
