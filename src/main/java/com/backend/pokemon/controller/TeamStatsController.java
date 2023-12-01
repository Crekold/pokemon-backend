package com.backend.pokemon.controller;

import com.backend.pokemon.dto.ResponseDTO;
import com.backend.pokemon.dto.TeamStatsDTO;
import com.backend.pokemon.service.TeamStatsService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/team-stats")
public class TeamStatsController {

    private static final Logger LOG = LoggerFactory.getLogger(TeamStatsController.class);

    private final TeamStatsService teamStatsService;

    @Autowired
    public TeamStatsController(TeamStatsService teamStatsService) {
        this.teamStatsService = teamStatsService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createTeamStats(@RequestBody TeamStatsDTO teamStatsDTO) {
        LOG.info("API Acceso: Creación de TeamStats.");
        try {
            TeamStatsDTO createdTeamStats = teamStatsService.saveTeamStats(teamStatsDTO);
            return ResponseEntity.ok(new ResponseDTO("TS-0000", createdTeamStats, "TeamStats creado con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al crear TeamStats - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("TS-0060", null, "Error al crear TeamStats: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllTeamStats() {
        LOG.info("API Acceso: Obtener todos los TeamStats.");
        try {
            List<TeamStatsDTO> teamStats = teamStatsService.getAllTeamStats();
            return ResponseEntity.ok(new ResponseDTO("TS-0000", teamStats, "TeamStats obtenidos con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al obtener TeamStats - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("TS-0061", null, "Error al obtener TeamStats: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getTeamStatsById(@PathVariable Long id) {
        LOG.info("API Acceso: Obtener TeamStats con ID: {}", id);
        try {
            TeamStatsDTO teamStats = teamStatsService.getTeamStatsById(id);
            return ResponseEntity.ok(new ResponseDTO("TS-0000", teamStats, "TeamStats obtenido con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al obtener TeamStats - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("TS-0062", null, "Error al obtener TeamStats: " + e.getMessage()));
        }
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<ResponseDTO> getTeamStatsByTeamId(@PathVariable Long teamId) {
        LOG.info("API Acceso: Obtener TeamStats con ID de equipo: {}", teamId);
        try {
            TeamStatsDTO teamStats = teamStatsService.getTeamStatsByTeamId(teamId);
            return ResponseEntity.ok(new ResponseDTO("TS-0000", teamStats, "TeamStats obtenido con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al obtener TeamStats - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("TS-0062", null, "Error al obtener TeamStats: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteTeamStats(@PathVariable Long id) {
        LOG.info("API Acceso: Eliminación de TeamStats con ID: {}", id);
        try {
            teamStatsService.deleteTeamStatsById(id);
            return ResponseEntity.ok(new ResponseDTO("TS-0001", null, "TeamStats eliminado correctamente"));
        } catch (Exception e) {
            LOG.error("API Error: Error al eliminar TeamStats - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("TS-0063", null, "Error al eliminar TeamStats: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateTeamStats(@PathVariable Long id, @RequestBody TeamStatsDTO teamStatsDTO) {
        LOG.info("API Acceso: Actualización de TeamStats con ID: {}", id);
        try {
            TeamStatsDTO updatedTeamStats = teamStatsService.updateTeamStats(id, teamStatsDTO);
            return ResponseEntity.ok(new ResponseDTO("TS-0002", updatedTeamStats, "TeamStats actualizado con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al actualizar TeamStats - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("TS-0064", null, "Error al actualizar TeamStats: " + e.getMessage()));
        }
    }
}