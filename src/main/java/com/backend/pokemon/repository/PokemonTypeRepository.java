package com.backend.pokemon.repository;

import com.backend.pokemon.model.PokemonType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonTypeRepository extends JpaRepository<PokemonType, Long> {}