package com.backend.pokemon.service;

import com.backend.pokemon.dto.PokemonDTO;
import com.backend.pokemon.dto.PokemonTypeDTO;
import com.backend.pokemon.dto.TypeElementDTO;
import com.backend.pokemon.model.Pokemon;
import com.backend.pokemon.model.PokemonType;
import com.backend.pokemon.model.TypeElement;
import com.backend.pokemon.repository.PokemonRepository;
import com.backend.pokemon.repository.PokemonTypeRepository;
import com.backend.pokemon.repository.TypeElementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PokemonTypeService {

    private static final Logger LOG = LoggerFactory.getLogger(PokemonTypeService.class);

    private final PokemonTypeRepository pokemonTypeRepository;
    private final PokemonRepository pokemonRepository;
    private final TypeElementRepository typeElementRepository;

    @Autowired
    public PokemonTypeService(
            PokemonTypeRepository pokemonTypeRepository,
            PokemonRepository pokemonRepository,
            TypeElementRepository typeElementRepository
    ) {
        this.pokemonTypeRepository = pokemonTypeRepository;
        this.pokemonRepository = pokemonRepository;
        this.typeElementRepository = typeElementRepository;
    }

    public PokemonTypeDTO savePokemonType(PokemonTypeDTO pokemonTypeDTO) {
        LOG.info("Guardando PokemonType: {}", pokemonTypeDTO);
        try {
            PokemonType pokemonType = convertDTOToEntity(pokemonTypeDTO);

            Optional<Pokemon> pokemonOptional = pokemonRepository.findById(pokemonTypeDTO.getPokemon().getPokemonId());
            Pokemon pokemon = pokemonOptional.orElse(null);
            pokemonType.setPokemon(pokemon);

            Optional<TypeElement> typeElementOptional = typeElementRepository.findById(pokemonTypeDTO.getTypeElement().getTypeElementId());
            TypeElement typeElement = typeElementOptional.orElse(null);
            pokemonType.setTypeElement(typeElement);

            PokemonType savedPokemonType = pokemonTypeRepository.save(pokemonType);
            return convertEntityToDTO(savedPokemonType);
        } catch (Exception e) {
            LOG.error("Error al guardar PokemonType: {}", e.getMessage(), e);
            throw new RuntimeException("Error al guardar PokemonType: " + e.getMessage(), e);
        }
    }

    public List<PokemonTypeDTO> findAllPokemonTypes() {
        LOG.info("Obteniendo todos los PokemonTypes");
        try {
            List<PokemonType> pokemonTypes = pokemonTypeRepository.findAll();
            return convertEntityListToDTOList(pokemonTypes);
        } catch (Exception e) {
            LOG.error("Error al obtener todos los PokemonTypes: {}", e.getMessage(), e);
            throw new RuntimeException("Error al obtener todos los PokemonTypes: " + e.getMessage(), e);
        }
    }

    public PokemonTypeDTO findPokemonTypeById(Long id) {
        LOG.info("Buscando PokemonType con ID: {}", id);
        try {
            PokemonType pokemonType = pokemonTypeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("PokemonType no encontrado con ID: " + id));
            return convertEntityToDTO(pokemonType);
        } catch (Exception e) {
            LOG.error("Error al buscar PokemonType por ID: {}", e.getMessage(), e);
            throw new RuntimeException("Error al buscar PokemonType por ID: " + e.getMessage(), e);
        }
    }

    public void deletePokemonType(Long id) {
        LOG.info("Eliminando PokemonType con ID: {}", id);
        try {
            pokemonTypeRepository.deleteById(id);
        } catch (Exception e) {
            LOG.error("Error al eliminar PokemonType por ID: {}", e.getMessage(), e);
            throw new RuntimeException("Error al eliminar PokemonType por ID: " + e.getMessage(), e);
        }
    }

    public PokemonTypeDTO updatePokemonType(Long id, PokemonTypeDTO newPokemonTypeDTO) {
        LOG.info("Actualizando PokemonType con ID: {}", id);
        try {
            PokemonType newPokemonTypeData = convertDTOToEntity(newPokemonTypeDTO);
            PokemonType updatedPokemonType = pokemonTypeRepository.findById(id)
                    .map(existingPokemonType -> {
                        existingPokemonType.setPokemon(newPokemonTypeData.getPokemon());
                        existingPokemonType.setTypeElement(newPokemonTypeData.getTypeElement());
                        return pokemonTypeRepository.save(existingPokemonType);
                    }).orElseThrow(() -> new RuntimeException("PokemonType no encontrado con ID: " + id));
            return convertEntityToDTO(updatedPokemonType);
        } catch (Exception e) {
            LOG.error("Error al actualizar PokemonType por ID: {}", e.getMessage(), e);
            throw new RuntimeException("Error al actualizar PokemonType por ID: " + e.getMessage(), e);
        }
    }

    private PokemonTypeDTO convertEntityToDTO(PokemonType pokemonType) {
        PokemonTypeDTO pokemonTypeDTO = new PokemonTypeDTO();
        pokemonTypeDTO.setPokemonTypeId(pokemonType.getPokemonTypeId());

        if (pokemonType.getPokemon() != null) {
            PokemonDTO pokemonDTO = new PokemonDTO(
                    pokemonType.getPokemon().getPokemonId(),
                    pokemonType.getPokemon().getPokemonName(),
                    pokemonType.getPokemon().getImageUrl()
            );
            pokemonTypeDTO.setPokemon(pokemonDTO);
        }

        if (pokemonType.getTypeElement() != null) {
            TypeElementDTO typeElementDTO = new TypeElementDTO(
                    pokemonType.getTypeElement().getTypeElementId(),
                    pokemonType.getTypeElement().getTypeElementName()
            );
            pokemonTypeDTO.setTypeElement(typeElementDTO);
        }

        return pokemonTypeDTO;
    }

    private List<PokemonTypeDTO> convertEntityListToDTOList(List<PokemonType> pokemonTypes) {
        return pokemonTypes.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    private PokemonType convertDTOToEntity(PokemonTypeDTO pokemonTypeDTO) {
        PokemonType pokemonType = new PokemonType();
        pokemonType.setPokemonTypeId(pokemonTypeDTO.getPokemonTypeId());

        Optional<Pokemon> pokemonOptional = pokemonRepository.findById(pokemonTypeDTO.getPokemon().getPokemonId());
        pokemonType.setPokemon(pokemonOptional.orElse(null));

        Optional<TypeElement> typeElementOptional = typeElementRepository.findById(pokemonTypeDTO.getTypeElement().getTypeElementId());
        pokemonType.setTypeElement(typeElementOptional.orElse(null));

        return pokemonType;
    }
}
