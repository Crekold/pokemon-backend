package com.backend.pokemon.repository;

import com.backend.pokemon.model.TeamPokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamPokemonRepository extends JpaRepository<TeamPokemon, Long> {
    // Aquí puedes añadir métodos personalizados si son necesarios
}
