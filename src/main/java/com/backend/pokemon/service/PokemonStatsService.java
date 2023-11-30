package com.backend.pokemon.service;

import com.backend.pokemon.dto.PokemonDTO;
import com.backend.pokemon.dto.PokemonStatsDTO;
import com.backend.pokemon.model.Pokemon;
import com.backend.pokemon.model.PokemonStats;
import com.backend.pokemon.repository.PokemonRepository;
import com.backend.pokemon.repository.PokemonStatsRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PokemonStatsService {

    private static final Logger LOG = LoggerFactory.getLogger(PokemonStatsService.class);

    private final PokemonStatsRepository pokemonStatsRepository;
    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonStatsService(PokemonStatsRepository pokemonStatsRepository, PokemonRepository pokemonRepository) {
        this.pokemonStatsRepository = pokemonStatsRepository;
        this.pokemonRepository = pokemonRepository;
    }

    @Transactional
    public PokemonStatsDTO savePokemonStats(PokemonStatsDTO pokemonStatsDTO) {
        LOG.info("Guardando PokemonStats: {}", pokemonStatsDTO);
        try {
            PokemonStats pokemonStats = convertDTOToEntity(pokemonStatsDTO);

            Optional<Pokemon> pokemonOptional = Optional.ofNullable(pokemonStatsDTO.getPokemon())
                    .map(PokemonDTO::getPokemonId)
                    .flatMap(pokemonRepository::findById);
            Pokemon pokemon = pokemonOptional.orElse(null);
            pokemonStats.setPokemon(pokemon);

            PokemonStats savedPokemonStats = pokemonStatsRepository.save(pokemonStats);
            return convertEntityToDTO(savedPokemonStats);
        } catch (Exception e) {
            LOG.error("Error al guardar PokemonStats: {}", e.getMessage(), e);
            throw new RuntimeException("Error al guardar PokemonStats: " + e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    public List<PokemonStatsDTO> findAllPokemonStats() {
        LOG.info("Obteniendo todos los PokemonStats");
        try {
            List<PokemonStats> pokemonStatsList = pokemonStatsRepository.findAll();
            return convertEntityListToDTOList(pokemonStatsList);
        } catch (Exception e) {
            LOG.error("Error al obtener todos los PokemonStats: {}", e.getMessage(), e);
            throw new RuntimeException("Error al obtener todos los PokemonStats: " + e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    public PokemonStatsDTO findPokemonStatsById(Long id) {
        LOG.info("Buscando PokemonStats con ID: {}", id);
        try {
            PokemonStats pokemonStats = pokemonStatsRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("PokemonStats no encontrado con ID: " + id));
            return convertEntityToDTO(pokemonStats);
        } catch (Exception e) {
            LOG.error("Error al buscar PokemonStats por ID: {}", e.getMessage(), e);
            throw new RuntimeException("Error al buscar PokemonStats por ID: " + e.getMessage(), e);
        }
    }

    @Transactional
    public void deletePokemonStats(Long id) {
        LOG.info("Eliminando PokemonStats con ID: {}", id);
        try {
            pokemonStatsRepository.deleteById(id);
        } catch (Exception e) {
            LOG.error("Error al eliminar PokemonStats por ID: {}", e.getMessage(), e);
            throw new RuntimeException("Error al eliminar PokemonStats por ID: " + e.getMessage(), e);
        }
    }

    @Transactional
    public PokemonStatsDTO updatePokemonStats(Long id, PokemonStatsDTO newPokemonStatsDTO) {
        LOG.info("Actualizando PokemonStats con ID: {}", id);
        try {
            PokemonStats newPokemonStatsData = convertDTOToEntity(newPokemonStatsDTO);
            PokemonStats updatedPokemonStats = pokemonStatsRepository.findById(id)
                    .map(existingPokemonStats -> {
                        existingPokemonStats.setHp(newPokemonStatsData.getHp());
                        existingPokemonStats.setAttack(newPokemonStatsData.getAttack());
                        existingPokemonStats.setDefense(newPokemonStatsData.getDefense());
                        existingPokemonStats.setSpecialAttack(newPokemonStatsData.getSpecialAttack());
                        existingPokemonStats.setSpecialDefense(newPokemonStatsData.getSpecialDefense());
                        existingPokemonStats.setPokemon(newPokemonStatsData.getPokemon());
                        return pokemonStatsRepository.save(existingPokemonStats);
                    }).orElseThrow(() -> new RuntimeException("PokemonStats no encontrado con ID: " + id));
            return convertEntityToDTO(updatedPokemonStats);
        } catch (Exception e) {
            LOG.error("Error al actualizar PokemonStats por ID: {}", e.getMessage(), e);
            throw new RuntimeException("Error al actualizar PokemonStats por ID: " + e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    public List<PokemonStatsDTO> findPokemonStatsByPokemonId(Long pokemonId) {
        LOG.info("Obteniendo todos los PokemonStats para el Pokemon con ID: {}", pokemonId);
        try {
            String pokemonIdString = String.valueOf(pokemonId);
            List<PokemonStats> pokemonStatsList = pokemonStatsRepository.findByPokemon_PokemonId(pokemonIdString);
            return convertEntityListToDTOList(pokemonStatsList);
        } catch (Exception e) {
            LOG.error("Error al obtener PokemonStats para el Pokemon - {}", e.getMessage(), e);
            throw new RuntimeException("Error al obtener PokemonStats para el Pokemon: " + e.getMessage(), e);
        }
    }

    private PokemonStatsDTO convertEntityToDTO(PokemonStats pokemonStats) {
        PokemonStatsDTO pokemonStatsDTO = new PokemonStatsDTO();
        pokemonStatsDTO.setIdStats(pokemonStats.getIdStats());
        pokemonStatsDTO.setHp(pokemonStats.getHp());
        pokemonStatsDTO.setAttack(pokemonStats.getAttack());
        pokemonStatsDTO.setDefense(pokemonStats.getDefense());
        pokemonStatsDTO.setSpecialAttack(pokemonStats.getSpecialAttack());
        pokemonStatsDTO.setSpecialDefense(pokemonStats.getSpecialDefense());

        if (pokemonStats.getPokemon() != null) {
            PokemonDTO pokemonDTO = new PokemonDTO(
                pokemonStats.getPokemon().getPokemonId(),
                pokemonStats.getPokemon().getPokemonName(),
                pokemonStats.getPokemon().getImageUrl()
            );
            pokemonStatsDTO.setPokemon(pokemonDTO);
        }

        return pokemonStatsDTO;
    }

    private List<PokemonStatsDTO> convertEntityListToDTOList(List<PokemonStats> pokemonStatsList) {
        return pokemonStatsList.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    private PokemonStats convertDTOToEntity(PokemonStatsDTO pokemonStatsDTO) {
        PokemonStats pokemonStats = new PokemonStats();
        pokemonStats.setIdStats(pokemonStatsDTO.getIdStats());
        pokemonStats.setHp(pokemonStatsDTO.getHp());
        pokemonStats.setAttack(pokemonStatsDTO.getAttack());
        pokemonStats.setDefense(pokemonStatsDTO.getDefense());
        pokemonStats.setSpecialAttack(pokemonStatsDTO.getSpecialAttack());
        pokemonStats.setSpecialDefense(pokemonStatsDTO.getSpecialDefense());

        return pokemonStats;
    }
}
