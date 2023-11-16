package com.backend.pokemon.service;

import com.backend.pokemon.model.User;
import com.backend.pokemon.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User saveUser(User user) {
        try {
            LOG.info("Guardando un usuario en la base de datos.");
            // En este punto, podrías agregar cualquier lógica adicional antes de guardar el usuario
            return userRepository.save(user);
        } catch (Exception e) {
            LOG.error("Error al guardar el usuario: " + e.getMessage(), e);
            throw new RuntimeException("Error al guardar el usuario.", e);
        }
    }

    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        try {
            LOG.info("Recuperando todos los usuarios de la base de datos.");
            return userRepository.findAll();
        } catch (Exception e) {
            LOG.error("Error al recuperar los usuarios: " + e.getMessage(), e);
            throw new RuntimeException("Error al recuperar los usuarios.", e);
        }
    }

    @Transactional(readOnly = true)
    public User findUserById(String userId) {
        try {
            LOG.info("Recuperando un usuario de la base de datos con ID: {}", userId);
            return userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
        } catch (Exception e) {
            LOG.error("Error al recuperar el usuario: " + e.getMessage(), e);
            throw new RuntimeException("Error al recuperar el usuario.", e);
        }
    }

    @Transactional
    public void deleteUser(String userId) {
        try {
            LOG.info("Eliminando un usuario de la base de datos con ID: {}", userId);
            userRepository.deleteById(userId);
        } catch (Exception e) {
            LOG.error("Error al eliminar el usuario: " + e.getMessage(), e);
            throw new RuntimeException("Error al eliminar el usuario.", e);
        }
    }

    @Transactional
    public User updateUser(String userId, User user) {
        try {
            LOG.info("Actualizando un usuario en la base de datos con ID: {}", userId);
            return userRepository.findById(userId)
                    .map(existingUser -> {
                        existingUser.setNickname(user.getNickname());
                        // Aquí puedes actualizar otros campos si es necesario
                        return userRepository.save(existingUser);
                    }).orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
        } catch (Exception e) {
            LOG.error("Error al actualizar el usuario: " + e.getMessage(), e);
            throw new RuntimeException("Error al actualizar el usuario.", e);
        }
    }

    public boolean existsById(String userId) {
        return userRepository.existsById(userId);
    }
}
