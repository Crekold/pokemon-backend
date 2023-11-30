package com.backend.pokemon.service;

import com.backend.pokemon.model.User;
import com.backend.pokemon.repository.UserRepository;
import com.backend.pokemon.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User saveUser(UserDTO userDTO) {
        try {
            LOG.info("Guardando un usuario en la base de datos.");
            User user = convertDTOToEntity(userDTO);
            // En este punto, podrías agregar cualquier lógica adicional antes de guardar el usuario
            return userRepository.save(user);
        } catch (Exception e) {
            LOG.error("Error al guardar el usuario: " + e.getMessage(), e);
            throw new RuntimeException("Error al guardar el usuario.", e);
        }
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAllUsers() {
        try {
            LOG.info("Recuperando todos los usuarios de la base de datos.");
            List<User> users = userRepository.findAll();
            return users.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error("Error al recuperar los usuarios: " + e.getMessage(), e);
            throw new RuntimeException("Error al recuperar los usuarios.", e);
        }
    }

    @Transactional(readOnly = true)
    public UserDTO findUserById(String userId) {
        try {
            LOG.info("Recuperando un usuario de la base de datos con ID: {}", userId);
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
            return convertEntityToDTO(user);
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
    public UserDTO updateUser(String userId, UserDTO userDTO) {
        try {
            LOG.info("Actualizando un usuario en la base de datos con ID: {}", userId);
            return userRepository.findById(userId)
                    .map(existingUser -> {
                        existingUser.setNickname(userDTO.getNickname());
                        // Aquí puedes actualizar otros campos si es necesario
                        userRepository.save(existingUser);
                        return convertEntityToDTO(existingUser);
                    }).orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
        } catch (Exception e) {
            LOG.error("Error al actualizar el usuario: " + e.getMessage(), e);
            throw new RuntimeException("Error al actualizar el usuario.", e);
        }
    }

    @Transactional
    public Map<String, Object> createOrLoginUser(String userId, String nickname) {
        Map<String, Object> result = new HashMap<>();
        try {
            User user = userRepository.findById(userId).orElse(null);
            LOG.info("SERVICE - Create or Login de usuario con ID: {}", userId);
            if (user == null) {
                User newUser = new User(userId, nickname);
                userRepository.save(newUser);
                LOG.info("SERVICE - Usuario creado con ID: {}", userId);
                result.put("user", convertEntityToDTO(newUser));
                result.put("message", "Usuario creado con éxito");
            } else {
                LOG.info("SERVICE - Usuario encontrado con ID: {}", userId);
                result.put("user", convertEntityToDTO(user));
                result.put("message", "Inicio de sesión exitoso");
            }
        } catch (Exception e) {
            LOG.error("Error en createOrLoginUser: " + e.getMessage(), e);
            throw new RuntimeException("Error en createOrLoginUser.", e);
        }

        return result;
    }

    // Métodos auxiliares para la conversión de UserDTO a User y viceversa
    private User convertDTOToEntity(UserDTO userDTO) {
        return new User(userDTO.getUserId(), userDTO.getNickname());
    }

    private UserDTO convertEntityToDTO(User user) {
        return new UserDTO(user.getUserId(), user.getNickname());
    }
}
