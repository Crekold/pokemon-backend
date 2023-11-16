package com.backend.pokemon.service;

import com.backend.pokemon.model.Team;
import com.backend.pokemon.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TeamService {

    private static final Logger LOG = LoggerFactory.getLogger(TeamService.class);

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Transactional
    public Team saveTeam(Team team) {
        LOG.info("Guardando un nuevo Team en la base de datos: {}", team);
        return teamRepository.save(team);
    }

    @Transactional(readOnly = true)
    public List<Team> findAllTeams() {
        LOG.info("Recuperando todos los Teams de la base de datos");
        return teamRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Team findTeamById(Long id) {
        LOG.info("Recuperando un Team de la base de datos con ID: {}", id);
        return teamRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Team no encontrado con ID: " + id));
    }

    @Transactional
    public void deleteTeam(Long id) {
        LOG.info("Eliminando Team con ID: {}", id);
        teamRepository.deleteById(id);
    }

    @Transactional
    public Team updateTeam(Long id, Team newTeamData) {
        LOG.info("Actualizando Team en la base de datos con ID: {}", id);
        return teamRepository.findById(id)
            .map(existingTeam -> {
                existingTeam.setTeamName(newTeamData.getTeamName());
                existingTeam.setUser(newTeamData.getUser());
                return teamRepository.save(existingTeam);
            }).orElseThrow(() -> new RuntimeException("Team no encontrado con ID: " + id));
    }
}
