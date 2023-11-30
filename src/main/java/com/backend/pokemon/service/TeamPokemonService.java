package com.backend.pokemon.service;

import com.backend.pokemon.dto.TeamPokemonDTO;
import com.backend.pokemon.dto.UserDTO;
import com.backend.pokemon.dto.PokemonDTO;
import com.backend.pokemon.dto.TeamDTO;
import com.backend.pokemon.model.TeamPokemon;
import com.backend.pokemon.model.Pokemon;
import com.backend.pokemon.model.Team;
import com.backend.pokemon.repository.PokemonRepository;
import com.backend.pokemon.repository.TeamPokemonRepository;
import com.backend.pokemon.repository.TeamRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamPokemonService {

    private static final Logger LOG = LoggerFactory.getLogger(TeamPokemonService.class);

    private final TeamPokemonRepository teamPokemonRepository;
    private final PokemonRepository pokemonRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public TeamPokemonService(TeamPokemonRepository teamPokemonRepository, PokemonRepository pokemonRepository, TeamRepository teamRepository) {
        this.teamPokemonRepository = teamPokemonRepository;
        this.pokemonRepository = pokemonRepository;
        this.teamRepository = teamRepository;
    }

    @Transactional
    public TeamPokemonDTO saveTeamPokemon(TeamPokemonDTO teamPokemonDTO) {
        LOG.info("Guardando TeamPokemon: {}", teamPokemonDTO);
        try {
            TeamPokemon teamPokemon = convertDTOToEntity(teamPokemonDTO);

            Optional<Pokemon> pokemonOptional = Optional.ofNullable(teamPokemonDTO.getPokemon())
                    .map(PokemonDTO::getPokemonId)
                    .flatMap(pokemonRepository::findById);
            Pokemon pokemon = pokemonOptional.orElse(null);
            teamPokemon.setPokemon(pokemon);

            Optional<Team> teamOptional = Optional.ofNullable(teamPokemonDTO.getTeam())
                    .map(TeamDTO::getTeamId)
                    .flatMap(teamRepository::findById);
            Team team = teamOptional.orElse(null);
            teamPokemon.setTeam(team);

            TeamPokemon savedTeamPokemon = teamPokemonRepository.save(teamPokemon);
            return convertEntityToDTO(savedTeamPokemon);
        } catch (Exception e) {
            LOG.error("Error al guardar TeamPokemon: {}", e.getMessage(), e);
            throw new RuntimeException("Error al guardar TeamPokemon: " + e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    public List<TeamPokemonDTO> findAllTeamPokemons() {
        LOG.info("Obteniendo todos los TeamPokemons");
        try {
            List<TeamPokemon> teamPokemons = teamPokemonRepository.findAll();
            return convertEntityListToDTOList(teamPokemons);
        } catch (Exception e) {
            LOG.error("Error al obtener todos los TeamPokemons: {}", e.getMessage(), e);
            throw new RuntimeException("Error al obtener todos los TeamPokemons: " + e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    public TeamPokemonDTO findTeamPokemonById(Long id) {
        LOG.info("Buscando TeamPokemon con ID: {}", id);
        try {
            TeamPokemon teamPokemon = teamPokemonRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("TeamPokemon no encontrado con ID: " + id));
            return convertEntityToDTO(teamPokemon);
        } catch (Exception e) {
            LOG.error("Error al buscar TeamPokemon por ID: {}", e.getMessage(), e);
            throw new RuntimeException("Error al buscar TeamPokemon por ID: " + e.getMessage(), e);
        }
    }

    @Transactional
    public void deleteTeamPokemon(Long id) {
        LOG.info("Eliminando TeamPokemon con ID: {}", id);
        try {
            teamPokemonRepository.deleteById(id);
        } catch (Exception e) {
            LOG.error("Error al eliminar TeamPokemon por ID: {}", e.getMessage(), e);
            throw new RuntimeException("Error al eliminar TeamPokemon por ID: " + e.getMessage(), e);
        }
    }

    @Transactional
    public TeamPokemonDTO updateTeamPokemon(Long id, TeamPokemonDTO newTeamPokemonDTO) {
        LOG.info("Actualizando TeamPokemon con ID: {}", id);
        try {
            TeamPokemon existingTeamPokemon = teamPokemonRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("TeamPokemon no encontrado con ID: " + id));

            // Actualizar el Pokemon si se proporciona en el DTO
            if (newTeamPokemonDTO.getPokemon() != null && newTeamPokemonDTO.getPokemon().getPokemonId() != null) {
                Optional<Pokemon> pokemonOptional = pokemonRepository.findById(newTeamPokemonDTO.getPokemon().getPokemonId());
                existingTeamPokemon.setPokemon(pokemonOptional.orElse(null));
            }

            // Actualizar el Team si se proporciona en el DTO
            if (newTeamPokemonDTO.getTeam() != null && newTeamPokemonDTO.getTeam().getTeamId() != null) {
                Optional<Team> teamOptional = teamRepository.findById(newTeamPokemonDTO.getTeam().getTeamId());
                existingTeamPokemon.setTeam(teamOptional.orElse(null));
            }

            // Guardar y devolver la entidad actualizada
            TeamPokemon updatedTeamPokemon = teamPokemonRepository.save(existingTeamPokemon);
            return convertEntityToDTO(updatedTeamPokemon);
        } catch (Exception e) {
            LOG.error("Error al actualizar TeamPokemon por ID: {}", e.getMessage(), e);
            throw new RuntimeException("Error al actualizar TeamPokemon por ID: " + e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    public List<TeamPokemonDTO> findTeamPokemonsByTeamId(Long teamId) {
        LOG.info("Obteniendo todos los TeamPokemons para el Team con ID: {}", teamId);
        try {
            List<TeamPokemon> teamPokemons = teamPokemonRepository.findByTeamTeamId(teamId);
            return convertEntityListToDTOList(teamPokemons);
        } catch (Exception e) {
            LOG.error("Error al obtener TeamPokemons para el Team - {}", e.getMessage(), e);
            throw new RuntimeException("Error al obtener TeamPokemons para el Team: " + e.getMessage(), e);
        }
    }

    private TeamPokemonDTO convertEntityToDTO(TeamPokemon teamPokemon) {
        TeamPokemonDTO teamPokemonDTO = new TeamPokemonDTO();
        teamPokemonDTO.setTeamPokemonId(teamPokemon.getTeamPokemonId());

        if (teamPokemon.getPokemon() != null) {
            PokemonDTO pokemonDTO = new PokemonDTO(
                    teamPokemon.getPokemon().getPokemonId(),
                    teamPokemon.getPokemon().getPokemonName(),
                    teamPokemon.getPokemon().getImageUrl()
            );
            teamPokemonDTO.setPokemon(pokemonDTO);
        }

        if (teamPokemon.getTeam() != null) {
            UserDTO userDTO = new UserDTO(
                    teamPokemon.getTeam().getUser().getUserId(),
                    teamPokemon.getTeam().getUser().getNickname()
            );
            TeamDTO teamDTO = new TeamDTO(
                    teamPokemon.getTeam().getTeamId(),
                    teamPokemon.getTeam().getTeamName(),
                    userDTO
            );
            teamPokemonDTO.setTeam(teamDTO);
        }

        return teamPokemonDTO;
    }

    private List<TeamPokemonDTO> convertEntityListToDTOList(List<TeamPokemon> teamPokemons) {
        return teamPokemons.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    private TeamPokemon convertDTOToEntity(TeamPokemonDTO teamPokemonDTO) {
        TeamPokemon teamPokemon = new TeamPokemon();
        teamPokemon.setTeamPokemonId(teamPokemonDTO.getTeamPokemonId());

        return teamPokemon;
    }
}
