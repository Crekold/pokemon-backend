package com.backend.pokemon.controller;

import com.backend.pokemon.model.User;
import com.backend.pokemon.service.UserService;
import com.backend.pokemon.dto.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> saveUser(@RequestBody User user) {
        LOG.info("Acceso API: Creación de usuario.");
        try {
            User savedUser = userService.saveUser(user);
            return ResponseEntity.ok(new ResponseDTO("U-0000", savedUser, "Usuario creado con éxito"));
        } catch (Exception e) {
            LOG.error("Acceso API: Error al crear usuario - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("U-0022", null, "Error al crear usuario: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllUsers() {
        LOG.info("Acceso API: Obtener todos los usuarios.");
        try {
            return ResponseEntity.ok(new ResponseDTO("U-0000", userService.findAllUsers(), "Usuarios obtenidos con éxito"));
        } catch (Exception e) {
            LOG.error("Acceso API: Error al obtener usuarios - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("U-0024", null, "Error al obtener usuarios: " + e.getMessage()));
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable("userId") String userId) {
        LOG.info("Acceso API: Obtener usuario con ID: {}", userId);
        try {
            return ResponseEntity.ok(new ResponseDTO("U-0000", userService.findUserById(userId), "Usuario obtenido con éxito"));
        } catch (Exception e) {
            LOG.error("Acceso API: Error al obtener usuario - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("U-0025", null, "Error al obtener usuario: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable("userId") String userId) {
        LOG.info("Acceso API: Eliminación de usuario con ID: {}", userId);
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok(new ResponseDTO("U-0001", null, "Usuario eliminado correctamente"));
        } catch (Exception e) {
            LOG.error("Acceso API: Error al eliminar usuario - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("U-0026", null, "Error al eliminar usuario: " + e.getMessage()));
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ResponseDTO> updateUser(@PathVariable("userId") String userId, @RequestBody User user) {
        LOG.info("Acceso API: Actualización de usuario con ID: {}", userId);
        try {
            User updatedUser = userService.updateUser(userId, user);
            return ResponseEntity.ok(new ResponseDTO("U-0002", updatedUser, "Usuario actualizado con éxito"));
        } catch (Exception e) {
            LOG.error("Acceso API: Error al actualizar usuario - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("U-0028", null, "Error al actualizar usuario: " + e.getMessage()));
        }
    }
}
