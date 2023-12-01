package com.backend.pokemon.service;

import com.backend.pokemon.dto.TeamDTO;
import com.backend.pokemon.dto.TeamSuggestionDTO;
import com.backend.pokemon.dto.UserDTO;
import com.backend.pokemon.model.Team;
import com.backend.pokemon.model.TeamSuggestion;
import com.backend.pokemon.repository.TeamRepository;
import com.backend.pokemon.repository.TeamSuggestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamSuggestionService {

    private static final Logger LOG = LoggerFactory.getLogger(TeamSuggestionService.class);

    private final TeamSuggestionRepository teamSuggestionRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public TeamSuggestionService(TeamSuggestionRepository teamSuggestionRepository, TeamRepository teamRepository) {
        this.teamSuggestionRepository = teamSuggestionRepository;
        this.teamRepository = teamRepository;
    }

    @Transactional
    public TeamSuggestionDTO saveTeamSuggestion(TeamSuggestionDTO teamSuggestionDTO) {
        LOG.info("Guardando sugerencia de equipo: {}", teamSuggestionDTO);
        try {
            TeamSuggestion teamSuggestion = convertDTOToEntity(teamSuggestionDTO);

            Team team = teamRepository.findById(teamSuggestionDTO.getTeam().getTeamId())
                    .orElseThrow(() -> new RuntimeException("Equipo no encontrado con ID: " + teamSuggestionDTO.getTeam().getTeamId()));
            teamSuggestion.setTeam(team);

            TeamSuggestion savedTeamSuggestion = teamSuggestionRepository.save(teamSuggestion);
            return convertEntityToDTO(savedTeamSuggestion);
        } catch (Exception e) {
            LOG.error("Error al guardar la sugerencia de equipo: ", e);
            throw new RuntimeException("No se pudieron guardar las sugerencias del equipo", e);
        }
    }

    @Transactional(readOnly = true)
    public List<TeamSuggestionDTO> getAllTeamSuggestions() {
        LOG.info("Intentando recuperar todas las sugerencias de equipo");
        try {
            List<TeamSuggestion> teamSuggestions = teamSuggestionRepository.findAll();
            LOG.info("Sugerencias de equipo recuperadas con éxito");
            return teamSuggestions.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error("Error al recuperar las sugerencias de equipo: ", e);
            throw new RuntimeException("No se pudieron recuperar las sugerencias de equipo", e);
        }
    }

    @Transactional(readOnly = true)
    public TeamSuggestionDTO getTeamSuggestionById(Long teamSuggestionId) {
        LOG.info("Intentando recuperar la sugerencia de equipo con ID: {}", teamSuggestionId);
        try {
            TeamSuggestion teamSuggestion = teamSuggestionRepository.findById(teamSuggestionId)
                    .orElseThrow(() -> new RuntimeException("Sugerencia de equipo no encontrada con ID: " + teamSuggestionId));
            LOG.info("Sugerencia de equipo recuperada con éxito con ID: {}", teamSuggestionId);
            return convertEntityToDTO(teamSuggestion);
        } catch (Exception e) {
            LOG.error("Error al recuperar la sugerencia de equipo con ID: {}: ", teamSuggestionId, e);
            throw new RuntimeException("No se pudieron recuperar las sugerencias de equipo con ID: " + teamSuggestionId, e);
        }
    }

    @Transactional
    public TeamSuggestionDTO updateTeamSuggestion(Long id, TeamSuggestionDTO newTeamSuggestionDTO) {
        LOG.info("Actualizando el TeamSuggestion con ID: {}", id);
        try {
            TeamSuggestion existingTeamSuggestion = teamSuggestionRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("TeamSuggestion no encontrado con ID: " + id));

            // Actualizar el Team si se proporciona en el DTO
            if (newTeamSuggestionDTO.getTeam() != null && newTeamSuggestionDTO.getTeam().getTeamId() != null) {
                Optional<Team> teamOptional = teamRepository.findById(newTeamSuggestionDTO.getTeam().getTeamId());
                existingTeamSuggestion.setTeam(teamOptional.orElse(null));
            }

            // Actualizar el campo suggestionExplanation
            existingTeamSuggestion.setSuggestionExplanation(newTeamSuggestionDTO.getSuggestionExplanation());

            // Guardar y devolver la entidad actualizada
            TeamSuggestion updatedTeamSuggestion = teamSuggestionRepository.save(existingTeamSuggestion);
            return convertEntityToDTO(updatedTeamSuggestion);
        } catch (Exception e) {
            LOG.error("Error al actualizar TeamSuggestion por ID: {}", e.getMessage(), e);
            throw new RuntimeException("Error al actualizar TeamSuggestion por ID: " + e.getMessage(), e);
        }
    }

    @Transactional
    public void deleteTeamSuggestionById(Long teamSuggestionId) {
        LOG.info("Intentando eliminar la sugerencia de equipo con ID: {}", teamSuggestionId);
        try {
            teamSuggestionRepository.deleteById(teamSuggestionId);
            LOG.info("Sugerencia de equipo eliminada con éxito con ID: {}", teamSuggestionId);
        } catch (Exception e) {
            LOG.error("Error al eliminar la sugerencia de equipo con ID: {}: ", teamSuggestionId, e);
            throw new RuntimeException("No se pudieron eliminar la sugerencia de equipo con ID: " + teamSuggestionId, e);
        }
    }

    @Transactional
    public TeamSuggestionDTO getTeamSuggestionByTeamId(Long teamId) {
        LOG.info("Intentando recuperar la sugerencia de equipo con ID de equipo: {}", teamId);
        try {
            TeamSuggestion teamSuggestion = teamSuggestionRepository.findByTeam_TeamId(teamId);
            LOG.info("Sugerencia de equipo recuperada con éxito con ID de equipo: {}", teamId);
            return convertEntityToDTO(teamSuggestion);
        } catch (Exception e) {
            LOG.error("Error al recuperar la sugerencia de equipo con ID de equipo: {}: ", teamId, e);
            throw new RuntimeException("No se pudieron recuperar las sugerencias de equipo con ID de equipo: " + teamId, e);
        }
    }

    // Métodos de conversión

    private TeamSuggestionDTO convertEntityToDTO(TeamSuggestion teamSuggestion) {
        TeamSuggestionDTO teamSuggestionDTO = new TeamSuggestionDTO();
        teamSuggestionDTO.setTeamSuggestionId(teamSuggestion.getTeamSuggestionId());
        teamSuggestionDTO.setSuggestionExplanation(teamSuggestion.getSuggestionExplanation());

        if (teamSuggestion.getTeam() != null) {
            teamSuggestionDTO.setTeam(new TeamDTO(teamSuggestion.getTeam().getTeamId(),
                    teamSuggestion.getTeam().getTeamName(),
                    new UserDTO(teamSuggestion.getTeam().getUser().getUserId(),
                            teamSuggestion.getTeam().getUser().getNickname())));
        }

        return teamSuggestionDTO;
    }

    private TeamSuggestion convertDTOToEntity(TeamSuggestionDTO teamSuggestionDTO) {
        TeamSuggestion teamSuggestion = new TeamSuggestion();
        teamSuggestion.setTeamSuggestionId(teamSuggestionDTO.getTeamSuggestionId());
        teamSuggestion.setSuggestionExplanation(teamSuggestionDTO.getSuggestionExplanation());

        if (teamSuggestionDTO.getTeam() != null) {
            Team team = new Team();
            team.setTeamId(teamSuggestionDTO.getTeam().getTeamId());
            teamSuggestion.setTeam(team);
        }

        return teamSuggestion;
    }
}
