package com.backend.pokemon.controller;

import com.backend.pokemon.dto.TeamPokemonDTO;
import com.backend.pokemon.service.TeamPokemonService;
import com.backend.pokemon.dto.ResponseDTO;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/team-pokemon")
public class TeamPokemonController {

    private static final Logger LOG = LoggerFactory.getLogger(TeamPokemonController.class);

    private final TeamPokemonService teamPokemonService;

    @Autowired
    public TeamPokemonController(TeamPokemonService teamPokemonService) {
        this.teamPokemonService = teamPokemonService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createTeamPokemon(@RequestBody TeamPokemonDTO teamPokemonDTO) {
        LOG.info("API Acceso: Creación de TeamPokemon.");
        try {
            TeamPokemonDTO createdTeamPokemon = teamPokemonService.saveTeamPokemon(teamPokemonDTO);
            return ResponseEntity.ok(new ResponseDTO("TP-0000", createdTeamPokemon, "TeamPokemon creado con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al crear TeamPokemon - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("TP-0060", null, "Error al crear TeamPokemon: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllTeamPokemons() {
        LOG.info("API Acceso: Obtener todos los TeamPokemons.");
        try {
            List<TeamPokemonDTO> teamPokemons = teamPokemonService.findAllTeamPokemons();
            return ResponseEntity.ok(new ResponseDTO("TP-0000", teamPokemons, "TeamPokemons obtenidos con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al obtener TeamPokemons - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("TP-0061", null, "Error al obtener TeamPokemons: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getTeamPokemonById(@PathVariable Long id) {
        LOG.info("API Acceso: Obtener TeamPokemon con ID: {}", id);
        try {
            TeamPokemonDTO teamPokemon = teamPokemonService.findTeamPokemonById(id);
            return ResponseEntity.ok(new ResponseDTO("TP-0000", teamPokemon, "TeamPokemon obtenido con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al obtener TeamPokemon - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("TP-0062", null, "Error al obtener TeamPokemon: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteTeamPokemon(@PathVariable Long id) {
        LOG.info("API Acceso: Eliminación de TeamPokemon con ID: {}", id);
        try {
            teamPokemonService.deleteTeamPokemon(id);
            return ResponseEntity.ok(new ResponseDTO("TP-0001", null, "TeamPokemon eliminado correctamente"));
        } catch (Exception e) {
            LOG.error("API Error: Error al eliminar TeamPokemon - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("TP-0063", null, "Error al eliminar TeamPokemon: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateTeamPokemon(@PathVariable Long id, @RequestBody TeamPokemonDTO teamPokemonDTO) {
        LOG.info("API Acceso: Actualización de TeamPokemon con ID: {}", id);
        try {
            TeamPokemonDTO updatedTeamPokemon = teamPokemonService.updateTeamPokemon(id, teamPokemonDTO);
            return ResponseEntity.ok(new ResponseDTO("TP-0002", updatedTeamPokemon, "TeamPokemon actualizado con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al actualizar TeamPokemon - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("TP-0064", null, "Error al actualizar TeamPokemon: " + e.getMessage()));
        }
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<ResponseDTO> getTeamPokemonsByTeamId(@PathVariable Long teamId) {
        LOG.info("API Acceso: Obtener todos los TeamPokemons para el Team con ID: {}", teamId);
        try {
            List<TeamPokemonDTO> teamPokemons = teamPokemonService.findTeamPokemonsByTeamId(teamId);
            return ResponseEntity.ok(new ResponseDTO("TP-0000", teamPokemons, "TeamPokemons para el Team obtenidos con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al obtener TeamPokemons para el Team - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("TP-0065", null, "Error al obtener TeamPokemons para el Team: " + e.getMessage()));
        }
    }
}
