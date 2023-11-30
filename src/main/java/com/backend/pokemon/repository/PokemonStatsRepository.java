package com.backend.pokemon.repository;

import com.backend.pokemon.model.PokemonStats;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonStatsRepository extends JpaRepository<PokemonStats, Long> {
    List<PokemonStats> findByPokemon_PokemonId(String pokemonId);
}
