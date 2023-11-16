package com.backend.pokemon.repository;

import com.backend.pokemon.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    // Aquí puedes añadir métodos personalizados si son necesarios
}
