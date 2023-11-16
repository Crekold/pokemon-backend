package com.backend.pokemon.repository;

import com.backend.pokemon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // Aquí puedes añadir métodos personalizados si son necesarios
}
