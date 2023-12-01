package com.backend.pokemon.service;

import com.backend.pokemon.dto.PokemonApiDto;
import com.backend.pokemon.model.Pokemon;
import com.backend.pokemon.model.PokemonStats;
import com.backend.pokemon.model.TypeElement;
import com.backend.pokemon.model.PokemonType;
import com.backend.pokemon.repository.PokemonRepository;
import com.backend.pokemon.repository.PokemonStatsRepository;
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
    private final PokemonStatsRepository pokemonStatsRepository;

    @Autowired
    public PokemonApiService(RestTemplate restTemplate,
                             PokemonRepository pokemonRepository,
                             TypeElementRepository typeElementRepository,
                             PokemonTypeRepository pokemonTypeRepository,
                             PokemonStatsRepository pokemonStatsRepository) {
        this.restTemplate = restTemplate;
        this.pokemonRepository = pokemonRepository;
        this.typeElementRepository = typeElementRepository;
        this.pokemonTypeRepository = pokemonTypeRepository;
        this.pokemonStatsRepository = pokemonStatsRepository;
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

            Pokemon pokemon = new Pokemon(String.valueOf(pokemonApiDto.getId()), pokemonApiDto.getName(), pokemonApiDto.getSprites().getFront_default());
            pokemonRepository.save(pokemon); 

            for (PokemonApiDto.TypeElementDto typeDto : pokemonApiDto.getTypes()) {
                TypeElement typeElement = typeElementRepository.findByTypeElementName(typeDto.getType().getName())
                        .orElseGet(() -> typeElementRepository.save(new TypeElement(typeDto.getType().getName())));
                pokemonTypeRepository.save(new PokemonType(pokemon, typeElement));
            }

            // Ahora manejar los stats
            PokemonStats stats = new PokemonStats();
            pokemonApiDto.getStats().forEach(statDto -> {
                switch (statDto.getStat().getName()) {
                    case "hp": stats.setHp(statDto.getBase_stat()); break;
                    case "attack": stats.setAttack(statDto.getBase_stat()); break;
                    case "defense": stats.setDefense(statDto.getBase_stat()); break;
                    case "special-attack": stats.setSpecialAttack(statDto.getBase_stat()); break;
                    case "special-defense": stats.setSpecialDefense(statDto.getBase_stat()); break;
                    // Añadir más casos según sea necesario
                }
            });
            stats.setPokemon(pokemon);
            pokemonStatsRepository.save(stats);

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
