package com.backend.pokemon.repository;

import com.backend.pokemon.model.TypeElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeElementRepository extends JpaRepository<TypeElement, Long> {
    Optional<TypeElement> findByTypeElementName(String name);
}
