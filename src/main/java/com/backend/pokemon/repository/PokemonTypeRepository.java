package com.backend.pokemon.repository;

import com.backend.pokemon.model.PokemonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonTypeRepository extends JpaRepository<PokemonType, Long> {
    // Métodos personalizados si son necesarios
}
