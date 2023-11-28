package com.backend.pokemon.controller;

import com.backend.pokemon.model.Pokemon;
import com.backend.pokemon.service.PokemonApiService;
import com.backend.pokemon.service.PokemonService;
import com.backend.pokemon.dto.ResponseDTO;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    private static final Logger LOG = LoggerFactory.getLogger(PokemonController.class);

    private final PokemonService pokemonService;
    private final PokemonApiService pokemonApiService; 

    @Autowired
    public PokemonController(PokemonService pokemonService, PokemonApiService pokemonApiService){
        this.pokemonService = pokemonService;
        this.pokemonApiService = pokemonApiService; 
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createPokemon(@RequestBody Pokemon pokemon) {
        LOG.info("API Acceso: Creación de Pokemon.");
        try {
            Pokemon createdPokemon = pokemonService.savePokemon(pokemon);
            return ResponseEntity.ok(new ResponseDTO("P-0000", createdPokemon, "Pokemon creado con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al crear Pokemon - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("P-0040", null, "Error al crear Pokemon: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllPokemons() {
        LOG.info("API Acceso: Obtener todos los Pokemons.");
        try {
            List<Pokemon> pokemons = pokemonService.findAllPokemons();
            return ResponseEntity.ok(new ResponseDTO("P-0000", pokemons, "Pokemons obtenidos con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al obtener Pokemons - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("P-0041", null, "Error al obtener Pokemons: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getPokemonById(@PathVariable String id) {
        LOG.info("API Acceso: Obtener Pokemon con ID: {}", id);
        try {
            Pokemon pokemon = pokemonService.findPokemonById(id);
            return ResponseEntity.ok(new ResponseDTO("P-0000", pokemon, "Pokemon obtenido con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al obtener Pokemon - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("P-0042", null, "Error al obtener Pokemon: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deletePokemon(@PathVariable String id) {
        LOG.info("API Acceso: Eliminación de Pokemon con ID: {}", id);
        try {
            pokemonService.deletePokemon(id);
            return ResponseEntity.ok(new ResponseDTO("P-0001", null, "Pokemon eliminado correctamente"));
        } catch (Exception e) {
            LOG.error("API Error: Error al eliminar Pokemon - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("P-0043", null, "Error al eliminar Pokemon: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updatePokemon(@PathVariable String id, @RequestBody Pokemon pokemon) {
        LOG.info("API Acceso: Actualización de Pokemon con ID: {}", id);
        try {
            Pokemon updatedPokemon = pokemonService.updatePokemon(id, pokemon);
            return ResponseEntity.ok(new ResponseDTO("P-0002", updatedPokemon, "Pokemon actualizado con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al actualizar Pokemon - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("P-0044", null, "Error al actualizar Pokemon: " + e.getMessage()));
        }
    }


    @PostMapping("/import-pokemon/{pokemonId}")
    public ResponseEntity<ResponseDTO> importPokemon(@PathVariable int pokemonId) {
        LOG.info("API Acceso: Importación de Pokemon desde la PokéAPI con ID: {}", pokemonId);
        try {
            Pokemon importedPokemon = pokemonApiService.importPokemonFromPokeApi(pokemonId);
            return ResponseEntity.ok(new ResponseDTO("P-0003", importedPokemon, "Pokemon importado con éxito desde la PokéAPI"));
        } catch (Exception e) {
            LOG.error("API Error: Error al importar Pokemon desde la PokéAPI - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("P-0045", null, "Error al importar Pokemon desde la PokéAPI: " + e.getMessage()));
        }
    }

    @GetMapping("/import-range")
    public ResponseEntity<ResponseDTO> importRangeOfPokemons(@RequestParam int startId, @RequestParam int endId) {
        try {
            List<Pokemon> pokemons = pokemonApiService.importRangeOfPokemonsFromPokeApi(startId, endId);
            return ResponseEntity.ok(new ResponseDTO("P-0006", pokemons, "Rango de Pokemon importados con éxito."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO("P-0046", null, "Error al importar rango de Pokemon: " + e.getMessage()));
        }
    }
}
