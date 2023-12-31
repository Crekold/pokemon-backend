package com.backend.pokemon.service;

import com.backend.pokemon.dto.CreateTeamRequestDTO;
import com.backend.pokemon.dto.TeamDTO;
import com.backend.pokemon.dto.UserDTO;
import com.backend.pokemon.model.Pokemon;
import com.backend.pokemon.model.PokemonStats;
import com.backend.pokemon.model.Team;
import com.backend.pokemon.model.TeamPokemon;
import com.backend.pokemon.model.TeamStats;
import com.backend.pokemon.model.User;
import com.backend.pokemon.repository.PokemonRepository;
import com.backend.pokemon.repository.PokemonStatsRepository;
import com.backend.pokemon.repository.TeamPokemonRepository;
import com.backend.pokemon.repository.TeamRepository;
import com.backend.pokemon.repository.TeamStatsRepository;
import com.backend.pokemon.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private static final Logger LOG = LoggerFactory.getLogger(TeamService.class);

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final PokemonRepository pokemonRepository;
    private final TeamPokemonRepository teamPokemonRepository;
    private final PokemonStatsRepository pokemonStatsRepository;
    private final TeamStatsRepository teamStatsRepository;


    @Autowired
    public TeamService(TeamRepository teamRepository,
                       UserRepository userRepository,
                       PokemonRepository pokemonRepository,
                       TeamPokemonRepository teamPokemonRepository,
                       PokemonStatsRepository pokemonStatsRepository,
                       TeamStatsRepository teamStatsRepository) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
        this.pokemonRepository = pokemonRepository;
        this.teamPokemonRepository = teamPokemonRepository;
        this.pokemonStatsRepository = pokemonStatsRepository;
        this.teamStatsRepository = teamStatsRepository;
    }


    @Transactional
    public TeamDTO saveTeam(TeamDTO teamDTO) {
        LOG.info("Guardando un nuevo Team: {}", teamDTO);
        try {
            Team team = convertDTOToEntity(teamDTO);

            Optional<User> userOptional = userRepository.findById(teamDTO.getUser().getUserId());
            User user = userOptional.orElse(null);
            team.setUser(user);

            Team savedTeam = teamRepository.save(team);
            return convertEntityToDTO(savedTeam);
        } catch (Exception e) {
            LOG.error("Error al guardar Team: {}", e.getMessage(), e);
            throw new RuntimeException("Error al guardar Team: " + e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    public List<TeamDTO> findAllTeams() {
        LOG.info("Recuperando todos los Teams de la base de datos");
        try {
            List<Team> teams = teamRepository.findAll();
            return convertEntityListToDTOList(teams);
        } catch (Exception e) {
            LOG.error("Error al obtener todos los Teams: {}", e.getMessage(), e);
            throw new RuntimeException("Error al obtener todos los Teams: " + e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    public TeamDTO findTeamById(Long id) {
        LOG.info("Recuperando un Team de la base de datos con ID: {}", id);
        try {
            Team team = teamRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Team no encontrado con ID: " + id));
            return convertEntityToDTO(team);
        } catch (Exception e) {
            LOG.error("Error al buscar Team por ID: {}", e.getMessage(), e);
            throw new RuntimeException("Error al buscar Team por ID: " + e.getMessage(), e);
        }
    }

    @Transactional
    public void deleteTeam(Long id) {
        LOG.info("Eliminando Team con ID: {}", id);
        try {
            teamRepository.deleteById(id);
        } catch (Exception e) {
            LOG.error("Error al eliminar Team por ID: {}", e.getMessage(), e);
            throw new RuntimeException("Error al eliminar Team por ID: " + e.getMessage(), e);
        }
    }

    @Transactional
    public TeamDTO updateTeam(Long id, TeamDTO newTeamDTO) {
        LOG.info("Actualizando Team con ID: {}", id);
        try {
            Team existingTeam = teamRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Team no encontrado con ID: " + id));

            // Actualiza solo si se proporciona un nuevo nombre de equipo
            if (newTeamDTO.getTeamName() != null) {
                existingTeam.setTeamName(newTeamDTO.getTeamName());
            }

            // Actualiza solo si se proporciona un nuevo usuario
            if (newTeamDTO.getUser() != null && newTeamDTO.getUser().getUserId() != null) {
                User user = userRepository.findById(newTeamDTO.getUser().getUserId())
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + newTeamDTO.getUser().getUserId()));
                existingTeam.setUser(user);
            }

            Team updatedTeam = teamRepository.save(existingTeam);
            return convertEntityToDTO(updatedTeam);
        } catch (Exception e) {
            LOG.error("Error al actualizar Team por ID: {}", e.getMessage(), e);
            throw new RuntimeException("Error al actualizar Team por ID: " + e.getMessage(), e);
        }
    }


    @Transactional(readOnly = true)
    public Team findLastTeamByUserId(String userId) {
        LOG.info("Recuperando el último Team creado por el usuario con ID: {}", userId);
        return teamRepository.findTopByUserUserIdOrderByTeamIdDesc(userId)
            .orElseThrow(() -> new RuntimeException("No se encontraron Teams para el usuario con ID: " + userId));
    }

    @Transactional(readOnly = true)
    public List<Team> findTeamsByUserId(String userId) {
        LOG.info("Recuperando todos los Teams del usuario con ID: {}", userId);
        return teamRepository.findByUserUserId(userId);
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

            int totalHp = 0, totalAttack = 0, totalDefense = 0, totalSpecialAttack = 0, totalSpecialDefense = 0;

            for (String pokemonId : request.getPokemonIds()) {
                Pokemon pokemon = pokemonRepository.findById(pokemonId)
                        .orElseThrow(() -> new RuntimeException("Pokemon no encontrado con ID: " + pokemonId));
                TeamPokemon teamPokemon = new TeamPokemon();
                teamPokemon.setPokemon(pokemon);
                teamPokemon.setTeam(team);
                teamPokemonRepository.save(teamPokemon);

                List<PokemonStats> pokemonStatsList = pokemonStatsRepository.findByPokemon_PokemonId(pokemonId);
                for (PokemonStats stats : pokemonStatsList) {
                    totalHp += stats.getHp();
                    totalAttack += stats.getAttack();
                    totalDefense += stats.getDefense();
                    totalSpecialAttack += stats.getSpecialAttack();
                    totalSpecialDefense += stats.getSpecialDefense();
                }
            }

            // Cálculo de estadísticas promedio y almacenamiento en team_stats
            int pokemonCount = request.getPokemonIds().size();
            TeamStats teamStats = new TeamStats(
                totalHp / pokemonCount,
                totalAttack / pokemonCount,
                totalDefense / pokemonCount,
                totalSpecialAttack / pokemonCount,
                totalSpecialDefense / pokemonCount,
                team
            );
            teamStatsRepository.save(teamStats);

            LOG.info("Equipo creado con éxito con nombre: {} y estadísticas promedio.", team.getTeamName());
            return team;
        } catch (Exception e) {
            LOG.error("Error al crear el equipo: {}", e.getMessage(), e);
            throw new RuntimeException("Error al crear el equipo: " + e.getMessage(), e);
        }
    }

    // Método para actualizar un equipo con nuevos Pokémon
    @Transactional
    public TeamDTO updateTeamWithPokemons(CreateTeamRequestDTO request) {
        LOG.info("Actualizando equipo con ID: {} y nombre: {}", request.getTeamId(), request.getTeamName());
        try {
            Team team = teamRepository.findById(request.getTeamId())
                    .orElseThrow(() -> new RuntimeException("Equipo no encontrado con ID: " + request.getTeamId()));

            if (request.getTeamName() != null && !request.getTeamName().isEmpty()) {
                team.setTeamName(request.getTeamName());
            }

            teamPokemonRepository.deleteByTeamTeamId(team.getTeamId());

            int totalHp = 0, totalAttack = 0, totalDefense = 0, totalSpecialAttack = 0, totalSpecialDefense = 0;

            for (String pokemonId : request.getPokemonIds()) {
                Pokemon pokemon = pokemonRepository.findById(pokemonId)
                        .orElseThrow(() -> new RuntimeException("Pokemon no encontrado con ID: " + pokemonId));
                TeamPokemon teamPokemon = new TeamPokemon();
                teamPokemon.setPokemon(pokemon);
                teamPokemon.setTeam(team);
                teamPokemonRepository.save(teamPokemon);

                List<PokemonStats> pokemonStatsList = pokemonStatsRepository.findByPokemon_PokemonId(pokemonId);
                for (PokemonStats stats : pokemonStatsList) {
                    totalHp += stats.getHp();
                    totalAttack += stats.getAttack();
                    totalDefense += stats.getDefense();
                    totalSpecialAttack += stats.getSpecialAttack();
                    totalSpecialDefense += stats.getSpecialDefense();
                }
            }

            // Cálculo de estadísticas promedio y actualización en team_stats
            int pokemonCount = request.getPokemonIds().size();
            TeamStats existingTeamStats = teamStatsRepository.findByTeam_TeamId(team.getTeamId());
            if (existingTeamStats != null) {
                existingTeamStats.setHpProm(totalHp / pokemonCount);
                existingTeamStats.setAttackProm(totalAttack / pokemonCount);
                existingTeamStats.setDefenseProm(totalDefense / pokemonCount);
                existingTeamStats.setSaProm(totalSpecialAttack / pokemonCount);
                existingTeamStats.setSeProm(totalSpecialDefense / pokemonCount);
                teamStatsRepository.save(existingTeamStats);
            }

            teamRepository.save(team);
            LOG.info("Equipo actualizado con éxito con nombre: {}", team.getTeamName());
            return convertEntityToDTO(team);
        } catch (Exception e) {
            LOG.error("Error al actualizar el equipo con ID: {}: {}", request.getTeamId(), e.getMessage(), e);
            throw new RuntimeException("Error al actualizar el equipo con ID: " + request.getTeamId() + ": " + e.getMessage(), e);
        }
    }


    private TeamDTO convertEntityToDTO(Team team) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setTeamId(team.getTeamId());
        teamDTO.setTeamName(team.getTeamName());

        if (team.getUser() != null) {
            UserDTO userDTO = new UserDTO(
                team.getUser().getUserId(),
                team.getUser().getNickname()
            );
            teamDTO.setUser(userDTO);
        }

        return teamDTO;
    }

    private List<TeamDTO> convertEntityListToDTOList(List<Team> teams) {
        return teams.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    private Team convertDTOToEntity(TeamDTO teamDTO) {
        Team team = new Team();
        team.setTeamId(teamDTO.getTeamId());
        team.setTeamName(teamDTO.getTeamName());
        return team;
    }
    
}
