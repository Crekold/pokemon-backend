package com.backend.pokemon.controller;

import com.backend.pokemon.dto.PokemonStatsDTO;
import com.backend.pokemon.dto.ResponseDTO;
import com.backend.pokemon.service.PokemonStatsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemon-stats")
public class PokemonStatsController {

    private static final Logger LOG = LoggerFactory.getLogger(PokemonStatsController.class);

    private final PokemonStatsService pokemonStatsService;

    @Autowired
    public PokemonStatsController(PokemonStatsService pokemonStatsService) {
        this.pokemonStatsService = pokemonStatsService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> savePokemonStats(@RequestBody PokemonStatsDTO pokemonStatsDTO) {
        LOG.info("API Acceso: Creación de PokemonStats.");
        try {
            PokemonStatsDTO createdPokemonStats = pokemonStatsService.savePokemonStats(pokemonStatsDTO);
            return ResponseEntity.ok(new ResponseDTO("PS-0000", createdPokemonStats, "PokemonStats creado con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al crear PokemonStats - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("PS-0060", null, "Error al crear PokemonStats: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllPokemonStats() {
        LOG.info("API Acceso: Obtener todos los PokemonStats.");
        try {
            List<PokemonStatsDTO> pokemonStatsList = pokemonStatsService.findAllPokemonStats();
            return ResponseEntity.ok(new ResponseDTO("PS-0000", pokemonStatsList, "PokemonStats obtenidos con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al obtener PokemonStats - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("PS-0061", null, "Error al obtener PokemonStats: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getPokemonStatsById(@PathVariable Long id) {
        LOG.info("API Acceso: Obtener PokemonStats con ID: {}", id);
        try {
            PokemonStatsDTO pokemonStats = pokemonStatsService.findPokemonStatsById(id);
            return ResponseEntity.ok(new ResponseDTO("PS-0000", pokemonStats, "PokemonStats obtenido con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al obtener PokemonStats - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("PS-0062", null, "Error al obtener PokemonStats: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deletePokemonStats(@PathVariable Long id) {
        LOG.info("API Acceso: Eliminación de PokemonStats con ID: {}", id);
        try {
            pokemonStatsService.deletePokemonStats(id);
            return ResponseEntity.ok(new ResponseDTO("PS-0001", null, "PokemonStats eliminado correctamente"));
        } catch (Exception e) {
            LOG.error("API Error: Error al eliminar PokemonStats - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("PS-0063", null, "Error al eliminar PokemonStats: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updatePokemonStats(@PathVariable Long id, @RequestBody PokemonStatsDTO pokemonStatsDTO) {
        LOG.info("API Acceso: Actualización de PokemonStats con ID: {}", id);
        try {
            PokemonStatsDTO updatedPokemonStats = pokemonStatsService.updatePokemonStats(id, pokemonStatsDTO);
            return ResponseEntity.ok(new ResponseDTO("PS-0002", updatedPokemonStats, "PokemonStats actualizado con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al actualizar PokemonStats - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("PS-0064", null, "Error al actualizar PokemonStats: " + e.getMessage()));
        }
    }

    @GetMapping("/pokemon/{pokemonId}")
    public ResponseEntity<ResponseDTO> getPokemonStatsByPokemonId(@PathVariable Long pokemonId) {
        LOG.info("API Acceso: Obtener todos los PokemonStats para el Pokemon con ID: {}", pokemonId);
        try {
            List<PokemonStatsDTO> pokemonStatsList = pokemonStatsService.findPokemonStatsByPokemonId(pokemonId);
            return ResponseEntity.ok(new ResponseDTO("PS-0000", pokemonStatsList, "PokemonStats para el Pokemon obtenidos con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al obtener PokemonStats para el Pokemon - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("PS-0065", null, "Error al obtener PokemonStats para el Pokemon: " + e.getMessage()));
        }
    }
}
