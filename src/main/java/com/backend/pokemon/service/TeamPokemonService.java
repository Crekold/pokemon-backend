package com.backend.pokemon.service;

import com.backend.pokemon.model.TeamPokemon;
import com.backend.pokemon.repository.TeamPokemonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TeamPokemonService {

    private static final Logger LOG = LoggerFactory.getLogger(TeamPokemonService.class);

    private final TeamPokemonRepository teamPokemonRepository;

    @Autowired
    public TeamPokemonService(TeamPokemonRepository teamPokemonRepository) {
        this.teamPokemonRepository = teamPokemonRepository;
    }

    @Transactional
    public TeamPokemon saveTeamPokemon(TeamPokemon teamPokemon) {
        LOG.info("Guardando un nuevo TeamPokemon en la base de datos: {}", teamPokemon);
        return teamPokemonRepository.save(teamPokemon);
    }

    @Transactional(readOnly = true)
    public List<TeamPokemon> findAllTeamPokemons() {
        LOG.info("Recuperando todos los TeamPokemons de la base de datos");
        return teamPokemonRepository.findAll();
    }

    @Transactional(readOnly = true)
    public TeamPokemon findTeamPokemonById(Long id) {
        LOG.info("Recuperando un TeamPokemon de la base de datos con ID: {}", id);
        return teamPokemonRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("TeamPokemon no encontrado con ID: " + id));
    }
    @Transactional(readOnly = true)
    public List<TeamPokemon> findTeamPokemonsByTeamId(Long teamId) {
        LOG.info("Recuperando TeamPokemons para el Team con ID: {}", teamId);
        return teamPokemonRepository.findByTeamTeamId(teamId);
    }

    @Transactional
    public void deleteTeamPokemon(Long id) {
        LOG.info("Eliminando TeamPokemon con ID: {}", id);
        teamPokemonRepository.deleteById(id);
    }

    @Transactional
    public TeamPokemon updateTeamPokemon(Long id, TeamPokemon newTeamPokemonData) {
        LOG.info("Actualizando TeamPokemon en la base de datos con ID: {}", id);
        return teamPokemonRepository.findById(id)
            .map(existingTeamPokemon -> {
                existingTeamPokemon.setPokemon(newTeamPokemonData.getPokemon());
                existingTeamPokemon.setTeam(newTeamPokemonData.getTeam());
                return teamPokemonRepository.save(existingTeamPokemon);
            }).orElseThrow(() -> new RuntimeException("TeamPokemon no encontrado con ID: " + id));
    }
}
