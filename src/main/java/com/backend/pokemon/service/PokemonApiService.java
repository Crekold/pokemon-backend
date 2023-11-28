package com.backend.pokemon.service;

import com.backend.pokemon.dto.PokemonApiDto;
import com.backend.pokemon.model.Pokemon;
import com.backend.pokemon.model.TypeElement;
import com.backend.pokemon.model.PokemonType;
import com.backend.pokemon.repository.PokemonRepository;
import com.backend.pokemon.repository.TypeElementRepository;
import com.backend.pokemon.repository.PokemonTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonApiService {

    private static final Logger LOG = LoggerFactory.getLogger(PokemonApiService.class);

    private final RestTemplate restTemplate;
    private final PokemonRepository pokemonRepository;
    private final TypeElementRepository typeElementRepository;
    private final PokemonTypeRepository pokemonTypeRepository;

    @Autowired
    public PokemonApiService(RestTemplate restTemplate,
                             PokemonRepository pokemonRepository,
                             TypeElementRepository typeElementRepository,
                             PokemonTypeRepository pokemonTypeRepository) {
        this.restTemplate = restTemplate;
        this.pokemonRepository = pokemonRepository;
        this.typeElementRepository = typeElementRepository;
        this.pokemonTypeRepository = pokemonTypeRepository;
    }

    @Transactional
    public Pokemon importPokemonFromPokeApi(int pokemonId) {
        try {
            LOG.info("Importando Pokémon desde la PokéAPI con ID: {}", pokemonId);
            final String url = "https://pokeapi.co/api/v2/pokemon/{id}";
            PokemonApiDto pokemonApiDto = restTemplate.getForObject(url, PokemonApiDto.class, pokemonId);

            if (pokemonApiDto == null) {
                LOG.error("No se recibieron datos para el Pokémon con ID: {}", pokemonId);
                throw new RuntimeException("No se pudo obtener datos del Pokémon desde la PokéAPI para el ID: " + pokemonId);
            }

            Pokemon pokemon = new Pokemon();
            pokemon.setPokemonId(String.valueOf(pokemonApiDto.getId()));
            pokemon.setPokemonName(pokemonApiDto.getName());
            pokemon.setImageUrl(pokemonApiDto.getSprites().getFront_default());
            pokemon = pokemonRepository.save(pokemon); 

            for (PokemonApiDto.TypeElementDto typeDto : pokemonApiDto.getTypes()) {
                TypeElement typeElement = typeElementRepository
                    .findByTypeElementName(typeDto.getType().getName())
                    .orElseGet(() -> {
                        TypeElement newTypeElement = new TypeElement();
                        newTypeElement.setTypeElementName(typeDto.getType().getName());
                        return typeElementRepository.save(newTypeElement);
                    });

                PokemonType pokemonType = new PokemonType();
                pokemonType.setPokemon(pokemon);
                pokemonType.setTypeElement(typeElement);
                pokemonTypeRepository.save(pokemonType);
            }
            return pokemon;
        } catch (Exception e) {
            LOG.error("Error al importar Pokémon desde la PokéAPI: ", e);
            throw new RuntimeException("Error al importar Pokémon desde la PokéAPI", e);
        }
    }

    @Transactional
    public List<Pokemon> importRangeOfPokemonsFromPokeApi(int startId, int endId) {
        List<Pokemon> pokemons = new ArrayList<>();
        for (int pokemonId = startId; pokemonId <= endId; pokemonId++) {
            try {
                Pokemon pokemon = importPokemonFromPokeApi(pokemonId);
                if (pokemon != null) {
                    pokemons.add(pokemon);
                }
            } catch (Exception e) {
                LOG.error("Error al importar rango de Pokémones desde la PokéAPI: ", e);
                throw new RuntimeException("Error al importar rango de Pokémones desde la PokéAPI", e);
            }
        }
        return pokemons;
    }
}
