package com.backend.pokemon.service;

import com.backend.pokemon.dto.TypeElementDTO;
import com.backend.pokemon.model.TypeElement;
import com.backend.pokemon.repository.TypeElementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeElementService {

    private static final Logger LOG = LoggerFactory.getLogger(TypeElementService.class);

    private final TypeElementRepository typeElementRepository;

    @Autowired
    public TypeElementService(TypeElementRepository typeElementRepository) {
        this.typeElementRepository = typeElementRepository;
    }

    @Transactional
    public TypeElementDTO saveTypeElement(TypeElementDTO typeElementDTO) {
        try {
            LOG.info("Guardando un nuevo tipo de elemento en la base de datos: {}", typeElementDTO);
            TypeElement typeElement = convertDTOToEntity(typeElementDTO);
            TypeElement savedTypeElement = typeElementRepository.save(typeElement);
            return convertEntityToDTO(savedTypeElement);
        } catch (Exception e) {
            LOG.error("Error al guardar el tipo de elemento: " + e.getMessage(), e);
            throw new RuntimeException("Error al guardar el tipo de elemento.", e);
        }
    }

    @Transactional(readOnly = true)
    public List<TypeElementDTO> findAllTypeElements() {
        try {
            LOG.info("Recuperando todos los tipos de elementos de la base de datos");
            List<TypeElement> typeElements = typeElementRepository.findAll();
            return convertEntitiesToDTOs(typeElements);
        } catch (Exception e) {
            LOG.error("Error al recuperar los tipos de elementos: " + e.getMessage(), e);
            throw new RuntimeException("Error al recuperar los tipos de elementos.", e);
        }
    }

    @Transactional(readOnly = true)
    public TypeElementDTO findTypeElementById(Long id) {
        try {
            LOG.info("Recuperando un tipo de elemento con ID: {}", id);
            TypeElement typeElement = typeElementRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Tipo de elemento no encontrado con ID: " + id));
            return convertEntityToDTO(typeElement);
        } catch (Exception e) {
            LOG.error("Error al recuperar el tipo de elemento: " + e.getMessage(), e);
            throw new RuntimeException("Error al recuperar el tipo de elemento.", e);
        }
    }

    @Transactional
    public void deleteTypeElement(Long id) {
        try {
            LOG.info("Eliminando tipo de elemento con ID: {}", id);
            typeElementRepository.deleteById(id);
        } catch (Exception e) {
            LOG.error("Error al eliminar el tipo de elemento: " + e.getMessage(), e);
            throw new RuntimeException("Error al eliminar el tipo de elemento.", e);
        }
    }

    @Transactional
    public TypeElementDTO updateTypeElement(Long id, TypeElementDTO newTypeElementData) {
        try {
            LOG.info("Actualizando tipo de elemento en la base de datos con ID: {}", id);
            TypeElement existingTypeElement = typeElementRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Tipo de elemento no encontrado con ID: " + id));

            existingTypeElement.setTypeElementName(newTypeElementData.getTypeElementName());

            TypeElement updatedTypeElement = typeElementRepository.save(existingTypeElement);
            return convertEntityToDTO(updatedTypeElement);
        } catch (Exception e) {
            LOG.error("Error al actualizar el tipo de elemento: " + e.getMessage(), e);
            throw new RuntimeException("Error al actualizar el tipo de elemento.", e);
        }
    }

    // Métodos de conversión DTO
    private TypeElementDTO convertEntityToDTO(TypeElement typeElement) {
        return new TypeElementDTO(typeElement.getTypeElementId(), typeElement.getTypeElementName());
    }

    private List<TypeElementDTO> convertEntitiesToDTOs(List<TypeElement> typeElements) {
        return typeElements.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    private TypeElement convertDTOToEntity(TypeElementDTO typeElementDTO) {
        TypeElement typeElement = new TypeElement();
        typeElement.setTypeElementId(typeElementDTO.getTypeElementId());
        typeElement.setTypeElementName(typeElementDTO.getTypeElementName());
        return typeElement;
    }
}
