package com.backend.pokemon.repository;

import com.backend.pokemon.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, String> {
    // Aquí puedes añadir métodos personalizados si son necesarios
}
