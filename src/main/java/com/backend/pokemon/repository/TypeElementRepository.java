package com.backend.pokemon.repository;

import com.backend.pokemon.model.TypeElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeElementRepository extends JpaRepository<TypeElement, Long> {
    // Aquí puedes añadir métodos personalizados si son necesarios
}
