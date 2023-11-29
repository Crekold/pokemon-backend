package com.backend.pokemon.service;

import com.backend.pokemon.dto.CreateTeamRequestDTO;
import com.backend.pokemon.model.Pokemon;
import com.backend.pokemon.model.Team;
import com.backend.pokemon.model.TeamPokemon;
import com.backend.pokemon.model.User;
import com.backend.pokemon.repository.PokemonRepository;
import com.backend.pokemon.repository.TeamPokemonRepository;
import com.backend.pokemon.repository.TeamRepository;
import com.backend.pokemon.repository.UserRepository;

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
    private final UserRepository userRepository;
    private final PokemonRepository pokemonRepository;
    private final TeamPokemonRepository teamPokemonRepository;


    @Autowired
    public TeamService(TeamRepository teamRepository,
                       UserRepository userRepository,
                       PokemonRepository pokemonRepository,
                       TeamPokemonRepository teamPokemonRepository) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
        this.pokemonRepository = pokemonRepository;
        this.teamPokemonRepository = teamPokemonRepository;
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

    @Transactional(readOnly = true)
public Team findLastTeamByUserId(String userId) {
    LOG.info("Recuperando el último Team creado por el usuario con ID: {}", userId);
    return teamRepository.findTopByUserUserIdOrderByTeamIdDesc(userId)
        .orElseThrow(() -> new RuntimeException("No se encontraron Teams para el usuario con ID: " + userId));
}


    @Transactional
    public Team createTeamWithPokemons(CreateTeamRequestDTO request) {
        try {
            LOG.info("Creando un nuevo equipo: {}", request.getTeamName());
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + request.getUserId()));

            Team team = new Team();
            team.setTeamName(request.getTeamName());
            team.setUser(user);
            team = teamRepository.save(team);

            for (String pokemonId : request.getPokemonIds()) {
                Pokemon pokemon = pokemonRepository.findById(pokemonId)
                        .orElseThrow(() -> new RuntimeException("Pokemon no encontrado con ID: " + pokemonId));
                TeamPokemon teamPokemon = new TeamPokemon();
                teamPokemon.setPokemon(pokemon);
                teamPokemon.setTeam(team);
                teamPokemonRepository.save(teamPokemon);
            }

            LOG.info("Equipo {} creado con éxito.", team.getTeamName());
            return team;
        } catch (Exception e) {
            LOG.error("Error al crear el equipo: {}", e.getMessage(), e);
            throw new RuntimeException("Error al crear el equipo: " + e.getMessage(), e);
        }
    }
    
}
