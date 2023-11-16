package com.backend.pokemon.controller;

import com.backend.pokemon.model.PokemonType;
import com.backend.pokemon.service.PokemonTypeService;
import com.backend.pokemon.dto.ResponseDTO; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pokemon-type")
public class PokemonTypeController {

    private static final Logger LOG = LoggerFactory.getLogger(PokemonTypeController.class);

    private final PokemonTypeService pokemonTypeService;

    @Autowired
    public PokemonTypeController(PokemonTypeService pokemonTypeService) {
        this.pokemonTypeService = pokemonTypeService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createPokemonType(@RequestBody PokemonType pokemonType) {
        LOG.info("API Acceso: Creación de PokemonType.");
        try {
            PokemonType createdPokemonType = pokemonTypeService.savePokemonType(pokemonType);
            return ResponseEntity.ok(new ResponseDTO("PT-0000", createdPokemonType, "PokemonType creado con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al crear PokemonType - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("PT-0060", null, "Error al crear PokemonType: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllPokemonTypes() {
        LOG.info("API Acceso: Obtener todos los PokemonTypes.");
        try {
            return ResponseEntity.ok(new ResponseDTO("PT-0000", pokemonTypeService.findAllPokemonTypes(), "PokemonTypes obtenidos con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al obtener PokemonTypes - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("PT-0061", null, "Error al obtener PokemonTypes: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getPokemonTypeById(@PathVariable Long id) {
        LOG.info("API Acceso: Obtener PokemonType con ID: {}", id);
        try {
            return ResponseEntity.ok(new ResponseDTO("PT-0000", pokemonTypeService.findPokemonTypeById(id), "PokemonType obtenido con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al obtener PokemonType - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("PT-0062", null, "Error al obtener PokemonType: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deletePokemonType(@PathVariable Long id) {
        LOG.info("API Acceso: Eliminación de PokemonType con ID: {}", id);
        try {
            pokemonTypeService.deletePokemonType(id);
            return ResponseEntity.ok(new ResponseDTO("PT-0001", null, "PokemonType eliminado correctamente"));
        } catch (Exception e) {
            LOG.error("API Error: Error al eliminar PokemonType - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("PT-0063", null, "Error al eliminar PokemonType: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updatePokemonType(@PathVariable Long id, @RequestBody PokemonType pokemonType) {
        LOG.info("API Acceso: Actualización de PokemonType con ID: {}", id);
        try {
            PokemonType updatedPokemonType = pokemonTypeService.updatePokemonType(id, pokemonType);
            return ResponseEntity.ok(new ResponseDTO("PT-0002", updatedPokemonType, "PokemonType actualizado con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al actualizar PokemonType - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("PT-0064", null, "Error al actualizar PokemonType: " + e.getMessage()));
        }
    }
}
