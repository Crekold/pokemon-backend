package com.backend.pokemon.service;

import com.backend.pokemon.dto.TeamDTO;
import com.backend.pokemon.dto.TeamStatsDTO;
import com.backend.pokemon.dto.UserDTO;
import com.backend.pokemon.model.Team;
import com.backend.pokemon.model.TeamStats;
import com.backend.pokemon.repository.TeamRepository;
import com.backend.pokemon.repository.TeamStatsRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamStatsService {

    private static final Logger LOG = LoggerFactory.getLogger(TeamStatsService.class);

    private final TeamStatsRepository teamStatsRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public TeamStatsService(TeamStatsRepository teamStatsRepository, TeamRepository teamRepository) {
        this.teamStatsRepository = teamStatsRepository;
        this.teamRepository = teamRepository;
    }

    @Transactional
    public TeamStatsDTO saveTeamStats(TeamStatsDTO teamStatsDTO) {
        LOG.info("Guardando las estadísticas del equipo: {}", teamStatsDTO);
        try {
            TeamStats teamStats = convertDTOToEntity(teamStatsDTO);

            Team team = teamRepository.findById(teamStatsDTO.getTeam().getTeamId())
                    .orElseThrow(() -> new RuntimeException("Equipo no encontrado con ID: " + teamStatsDTO.getTeam().getTeamId()));
            teamStats.setTeam(team);

            TeamStats savedTeamStats = teamStatsRepository.save(teamStats);
            return convertEntityToDTO(savedTeamStats);
        } catch (Exception e) {
            LOG.error("Error al guardar las estadísticas del equipo: ", e);
            throw new RuntimeException("No se pudieron guardar las estadísticas del equipo", e);
        }
    }

    @Transactional(readOnly = true)
    public List<TeamStatsDTO> getAllTeamStats() {
        LOG.info("Intentando recuperar todas las estadísticas de los equipos");
        try {
            List<TeamStats> teamStats = teamStatsRepository.findAll();
            LOG.info("Estadísticas de los equipos recuperadas con éxito");
            return teamStats.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error("Error al recuperar las estadísticas de los equipos: ", e);
            throw new RuntimeException("No se pudieron recuperar las estadísticas de los equipos", e);
        }
    }

    @Transactional(readOnly = true)
    public TeamStatsDTO getTeamStatsById(Long teamStatsId) {
        LOG.info("Intentando recuperar las estadísticas del equipo con ID: {}", teamStatsId);
        try {
            TeamStats teamStats = teamStatsRepository.findById(teamStatsId)
                    .orElseThrow(() -> new RuntimeException("Estadísticas del equipo no encontradas con ID: " + teamStatsId));
            LOG.info("Estadísticas del equipo recuperadas con éxito con ID: {}", teamStatsId);
            return convertEntityToDTO(teamStats);
        } catch (Exception e) {
            LOG.error("Error al recuperar las estadísticas del equipo con ID: {}: ", teamStatsId, e);
            throw new RuntimeException("No se pudieron recuperar las estadísticas del equipo con ID: " + teamStatsId, e);
        }
    }

    @Transactional(readOnly = true)
    public TeamStatsDTO getTeamStatsByTeamId(Long teamId) {
        LOG.info("Intentando recuperar las estadísticas del equipo con ID: {}", teamId);
        try {
            TeamStats teamStats = teamStatsRepository.findByTeam_TeamId(teamId);
            LOG.info("Estadísticas del equipo recuperadas con éxito con ID: {}", teamId);
            return convertEntityToDTO(teamStats);
        } catch (Exception e) {
            LOG.error("Error al recuperar las estadísticas del equipo con ID: {}: ", teamId, e);
            throw new RuntimeException("No se pudieron recuperar las estadísticas del equipo con ID: " + teamId, e);
        }
    }

    @Transactional
    public TeamStatsDTO updateTeamStats(Long id, TeamStatsDTO newTeamStatsDTO) {
        LOG.info("Actualizando las estadísticas del equipo con ID: {}", id);
        try {
            TeamStats existingTeamStats = teamStatsRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Estadísticas del equipo no encontradas con ID: " + id));

            // Actualizar el Team si se proporciona en el DTO
            if (newTeamStatsDTO.getTeam() != null && newTeamStatsDTO.getTeam().getTeamId() != null) {
                Optional<Team> teamOptional = teamRepository.findById(newTeamStatsDTO.getTeam().getTeamId());
                existingTeamStats.setTeam(teamOptional.orElse(null));
            }

            // Actualizar los valores de estadísticas del equipo
            existingTeamStats.setHpProm(newTeamStatsDTO.getHpProm());
            existingTeamStats.setAttackProm(newTeamStatsDTO.getAttackProm());
            existingTeamStats.setDefenseProm(newTeamStatsDTO.getDefenseProm());
            existingTeamStats.setSaProm(newTeamStatsDTO.getSaProm());
            existingTeamStats.setSeProm(newTeamStatsDTO.getSeProm());

            // Guardar y devolver la entidad actualizada
            TeamStats updatedTeamStats = teamStatsRepository.save(existingTeamStats);
            return convertEntityToDTO(updatedTeamStats);
        } catch (Exception e) {
            LOG.error("Error al actualizar las estadísticas del equipo por ID: {}", e.getMessage(), e);
            throw new RuntimeException("Error al actualizar las estadísticas del equipo por ID: " + e.getMessage(), e);
        }
    }


    @Transactional
    public void deleteTeamStatsById(Long teamStatsId) {
        LOG.info("Intentando eliminar las estadísticas del equipo con ID: {}", teamStatsId);
        try {
            teamStatsRepository.deleteById(teamStatsId);
            LOG.info("Estadísticas del equipo eliminadas con éxito con ID: {}", teamStatsId);
        } catch (Exception e) {
            LOG.error("Error al eliminar las estadísticas del equipo con ID: {}: ", teamStatsId, e);
            throw new RuntimeException("No se pudieron eliminar las estadísticas del equipo con ID: " + teamStatsId, e);
        }
    }
    
    // Métodos de conversión

    private TeamStatsDTO convertEntityToDTO(TeamStats teamStats) {
        TeamStatsDTO teamStatsDTO = new TeamStatsDTO();
        teamStatsDTO.setTeamStatsId(teamStats.getTeamStatsId());
        teamStatsDTO.setHpProm(teamStats.getHpProm());
        teamStatsDTO.setAttackProm(teamStats.getAttackProm());
        teamStatsDTO.setDefenseProm(teamStats.getDefenseProm());
        teamStatsDTO.setSaProm(teamStats.getSaProm());
        teamStatsDTO.setSeProm(teamStats.getSeProm());

        if (teamStats.getTeam() != null) {
            teamStatsDTO.setTeam(new TeamDTO(teamStats.getTeam().getTeamId(),
                                             teamStats.getTeam().getTeamName(),
                                             new UserDTO(teamStats.getTeam().getUser().getUserId(),
                                                         teamStats.getTeam().getUser().getNickname())));
        }

        return teamStatsDTO;
    }

    private TeamStats convertDTOToEntity(TeamStatsDTO teamStatsDTO) {
        TeamStats teamStats = new TeamStats();
        teamStats.setTeamStatsId(teamStatsDTO.getTeamStatsId());
        teamStats.setHpProm(teamStatsDTO.getHpProm());
        teamStats.setAttackProm(teamStatsDTO.getAttackProm());
        teamStats.setDefenseProm(teamStatsDTO.getDefenseProm());
        teamStats.setSaProm(teamStatsDTO.getSaProm());
        teamStats.setSeProm(teamStatsDTO.getSeProm());

        if (teamStatsDTO.getTeam() != null) {
            Team team = new Team();
            team.setTeamId(teamStatsDTO.getTeam().getTeamId());
            teamStats.setTeam(team);
        }

        return teamStats;
    }


}
