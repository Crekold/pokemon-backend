package com.backend.pokemon.controller;

import com.backend.pokemon.model.Team;
import com.backend.pokemon.service.TeamService;
import com.backend.pokemon.dto.CreateTeamRequestDTO;
import com.backend.pokemon.dto.ResponseDTO;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private static final Logger LOG = LoggerFactory.getLogger(TeamController.class);

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createTeam(@RequestBody Team team) {
        LOG.info("API Acceso: Creación de Team.");
        try {
            Team createdTeam = teamService.saveTeam(team);
            return ResponseEntity.ok(new ResponseDTO("T-0000", createdTeam, "Team creado con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al crear Team - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("T-0050", null, "Error al crear Team: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllTeams() {
        LOG.info("API Acceso: Obtener todos los Teams.");
        try {
            List<Team> teams = teamService.findAllTeams();
            return ResponseEntity.ok(new ResponseDTO("T-0000", teams, "Teams obtenidos con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al obtener Teams - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("T-0051", null, "Error al obtener Teams: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getTeamById(@PathVariable Long id) {
        LOG.info("API Acceso: Obtener Team con ID: {}", id);
        try {
            Team team = teamService.findTeamById(id);
            return ResponseEntity.ok(new ResponseDTO("T-0000", team, "Team obtenido con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al obtener Team - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("T-0052", null, "Error al obtener Team: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteTeam(@PathVariable Long id) {
        LOG.info("API Acceso: Eliminación de Team con ID: {}", id);
        try {
            teamService.deleteTeam(id);
            return ResponseEntity.ok(new ResponseDTO("T-0001", null, "Team eliminado correctamente"));
        } catch (Exception e) {
            LOG.error("API Error: Error al eliminar Team - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("T-0053", null, "Error al eliminar Team: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateTeam(@PathVariable Long id, @RequestBody Team team) {
        LOG.info("API Acceso: Actualización de Team con ID: {}", id);
        try {
            Team updatedTeam = teamService.updateTeam(id, team);
            return ResponseEntity.ok(new ResponseDTO("T-0002", updatedTeam, "Team actualizado con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al actualizar Team - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("T-0054", null, "Error al actualizar Team: " + e.getMessage()));
        }
    }
    
    @PostMapping("/create-with-pokemons")
    public ResponseEntity<ResponseDTO> createTeamWithPokemons(@RequestBody CreateTeamRequestDTO request) {
        LOG.info("API Acceso: Creación de Team con Pokémons.");
        try {
            Team team = teamService.createTeamWithPokemons(request);
            return ResponseEntity.ok(new ResponseDTO("T-0005", team, "Equipo creado con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al crear Team con Pokémons - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("T-0055", null, "Error al crear Team con Pokémons: " + e.getMessage()));
        }
    }
    //get para obtener los teams de un id de usuario
    @GetMapping("/last/{userId}")
public ResponseEntity<ResponseDTO> getLastTeamByUserId(@PathVariable String userId) {
    LOG.info("API Acceso: Obtener el último Team creado por el usuario con ID: {}", userId);
    try {
        Team lastTeam = teamService.findLastTeamByUserId(userId);
        return ResponseEntity.ok(new ResponseDTO("T-0000", lastTeam, "Último Team obtenido con éxito"));
    } catch (Exception e) {
        LOG.error("API Error: Error al obtener el último Team - " + e.getMessage(), e);
        return ResponseEntity.badRequest().body(new ResponseDTO("T-0056", null, "Error al obtener el último Team: " + e.getMessage()));
    }
}

}
