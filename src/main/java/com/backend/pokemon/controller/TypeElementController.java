package com.backend.pokemon.controller;

import com.backend.pokemon.service.TypeElementService;
import com.backend.pokemon.dto.ResponseDTO;
import com.backend.pokemon.dto.TypeElementDTO;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/type-elements")
public class TypeElementController {

    private static final Logger LOG = LoggerFactory.getLogger(TypeElementController.class);

    private final TypeElementService typeElementService;

    @Autowired
    public TypeElementController(TypeElementService typeElementService) {
        this.typeElementService = typeElementService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createTypeElement(@RequestBody TypeElementDTO typeElementDTO) {
        LOG.info("API Acceso: Creación de tipo de elemento.");
        try {
            TypeElementDTO createdTypeElement = typeElementService.saveTypeElement(typeElementDTO);
            return ResponseEntity.ok(new ResponseDTO("TE-0000", createdTypeElement, "Tipo de elemento creado con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al crear tipo de elemento - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("TE-0030", null, "Error al crear tipo de elemento: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllTypeElements() {
        LOG.info("API Acceso: Obtener todos los tipos de elementos.");
        try {
            List<TypeElementDTO> typeElements = typeElementService.findAllTypeElements();
            return ResponseEntity.ok(new ResponseDTO("TE-0000", typeElements, "Tipos de elemento obtenidos con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al obtener tipos de elementos - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("TE-0031", null, "Error al obtener tipos de elementos: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getTypeElementById(@PathVariable Long id) {
        LOG.info("API Acceso: Obtener tipo de elemento con ID: {}", id);
        try {
            TypeElementDTO typeElement = typeElementService.findTypeElementById(id);
            return ResponseEntity.ok(new ResponseDTO("TE-0000", typeElement, "Tipo de elemento obtenido con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al obtener tipo de elemento - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("TE-0032", null, "Error al obtener tipo de elemento: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteTypeElement(@PathVariable Long id) {
        LOG.info("API Acceso: Eliminación de tipo de elemento con ID: {}", id);
        try {
            typeElementService.deleteTypeElement(id);
            return ResponseEntity.ok(new ResponseDTO("TE-0001", null, "Tipo de elemento eliminado correctamente"));
        } catch (Exception e) {
            LOG.error("API Error: Error al eliminar tipo de elemento - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("TE-0033", null, "Error al eliminar tipo de elemento: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateTypeElement(@PathVariable Long id, @RequestBody TypeElementDTO typeElementDTO) {
        LOG.info("API Acceso: Actualización de tipo de elemento con ID: {}", id);
        try {
            TypeElementDTO updatedTypeElement = typeElementService.updateTypeElement(id, typeElementDTO);
            return ResponseEntity.ok(new ResponseDTO("TE-0002", updatedTypeElement, "Tipo de elemento actualizado con éxito"));
        } catch (Exception e) {
            LOG.error("API Error: Error al actualizar tipo de elemento - " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO("TE-0032", null, "Error al actualizar tipo de elemento: " + e.getMessage()));
        }
    }
}
