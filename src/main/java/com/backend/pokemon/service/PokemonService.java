package com.backend.pokemon.service;

import com.backend.pokemon.model.Pokemon;
import com.backend.pokemon.model.TypeElement;
import com.backend.pokemon.repository.PokemonRepository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PokemonService {

    private static final Logger LOG = LoggerFactory.getLogger(PokemonService.class);
    private final PokemonRepository pokemonRepository;
    private final TypeElementService typeElementService;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository, TypeElementService typeElementService) {
        this.pokemonRepository = pokemonRepository;
        this.typeElementService = typeElementService;
    }

    @Transactional
    public Pokemon savePokemonFromPokeApi(String pokemonId, String pokemonName, List<String> types, String imageUrl) {
        // Asumimos que el primer tipo en la lista es el tipo principal del Pokémon
        String primaryTypeName = types.isEmpty() ? "unknown" : types.get(0);
        TypeElement primaryType = typeElementService.saveOrGetTypeElement(primaryTypeName);

        // Crea y guarda el Pokémon
        Pokemon pokemon = new Pokemon();
        pokemon.setPokemonId(pokemonId);
        pokemon.setPokemonName(pokemonName);
        pokemon.setTypeElement(primaryType); // Asocia el TypeElement encontrado o creado
        pokemon.setFotoUrl(imageUrl);
        
        LOG.info("Guardando un nuevo Pokemon en la base de datos: {}", pokemon);
        return pokemonRepository.save(pokemon);
    }

    // ...otros métodos del servicio...
}

