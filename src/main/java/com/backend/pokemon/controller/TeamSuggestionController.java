package com.backend.pokemon.controller;

import com.backend.pokemon.dto.ResponseDTO;
import com.backend.pokemon.dto.TeamSuggestionDTO;
import com.backend.pokemon.service.TeamSuggestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team-suggestions")
public class TeamSuggestionController {

    private static final Logger LOG = LoggerFactory.getLogger(TeamSuggestionController.class);

    private final TeamSuggestionService teamSuggestionService;

    @Autowired
    public TeamSuggestionController(TeamSuggestionService teamSuggestionService) {
        this.teamSuggestionService = teamSuggestionService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createTeamSuggestion(@RequestBody TeamSuggestionDTO teamSuggestionDTO) {
        LOG.info("API Acceso: Creación de TeamSuggestion.");
        try {
            TeamSuggestionDTO createdTeamSuggestion = teamSuggestionService.saveTeamSuggestion(teamSuggestionDTO);
            return ResponseEntity.ok(new ResponseDTO("TS-0000", createdTeamSuggestion, "TeamSuggestion creado con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al crear TeamSuggestion - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("TS-0060", null, "Error al crear TeamSuggestion: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllTeamSuggestions() {
        LOG.info("API Acceso: Obtener todas las TeamSuggestions.");
        try {
            List<TeamSuggestionDTO> teamSuggestions = teamSuggestionService.getAllTeamSuggestions();
            return ResponseEntity.ok(new ResponseDTO("TS-0000", teamSuggestions, "TeamSuggestions obtenidos con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al obtener TeamSuggestions - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("TS-0061", null, "Error al obtener TeamSuggestions: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getTeamSuggestionById(@PathVariable Long id) {
        LOG.info("API Acceso: Obtener TeamSuggestion con ID: {}", id);
        try {
            TeamSuggestionDTO teamSuggestion = teamSuggestionService.getTeamSuggestionById(id);
            return ResponseEntity.ok(new ResponseDTO("TS-0000", teamSuggestion, "TeamSuggestion obtenido con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al obtener TeamSuggestion - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("TS-0062", null, "Error al obtener TeamSuggestion: " + e.getMessage()));
        }
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<ResponseDTO> getTeamSuggestionByTeamId(@PathVariable Long teamId) {
        LOG.info("API Acceso: Obtener TeamSuggestion con ID de Team: {}", teamId);
        try {
            TeamSuggestionDTO teamSuggestion = teamSuggestionService.getTeamSuggestionByTeamId(teamId);
            return ResponseEntity.ok(new ResponseDTO("TS-0000", teamSuggestion, "TeamSuggestion obtenido con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al obtener TeamSuggestion - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("TS-0062", null, "Error al obtener TeamSuggestion: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteTeamSuggestion(@PathVariable Long id) {
        LOG.info("API Acceso: Eliminación de TeamSuggestion con ID: {}", id);
        try {
            teamSuggestionService.deleteTeamSuggestionById(id);
            return ResponseEntity.ok(new ResponseDTO("TS-0001", null, "TeamSuggestion eliminado correctamente"));
        } catch (Exception e) {
            LOG.error("API Error: Error al eliminar TeamSuggestion - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("TS-0063", null, "Error al eliminar TeamSuggestion: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateTeamSuggestion(@PathVariable Long id, @RequestBody TeamSuggestionDTO teamSuggestionDTO) {
        LOG.info("API Acceso: Actualización de TeamSuggestion con ID: {}", id);
        try {
            TeamSuggestionDTO updatedTeamSuggestion = teamSuggestionService.updateTeamSuggestion(id, teamSuggestionDTO);
            return ResponseEntity.ok(new ResponseDTO("TS-0002", updatedTeamSuggestion, "TeamSuggestion actualizado con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al actualizar TeamSuggestion - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("TS-0064", null, "Error al actualizar TeamSuggestion: " + e.getMessage()));
        }
    }

}
