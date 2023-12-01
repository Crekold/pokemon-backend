package com.backend.pokemon.repository;

import com.backend.pokemon.model.TeamPokemon;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TeamPokemonRepository extends JpaRepository<TeamPokemon, Long> {
    List<TeamPokemon> findByTeamTeamId(Long teamId);

    // Añade esta línea para permitir la eliminación de todos los TeamPokemon de un equipo específico
    @Transactional
    void deleteByTeamTeamId(Long teamId);
}
