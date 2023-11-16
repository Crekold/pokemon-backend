package com.backend.pokemon.service;

import com.backend.pokemon.model.TypeElement;
import com.backend.pokemon.repository.TypeElementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TypeElementService {

    private static final Logger LOG = LoggerFactory.getLogger(TypeElementService.class);

    private final TypeElementRepository typeElementRepository;

    @Autowired
    public TypeElementService(TypeElementRepository typeElementRepository) {
        this.typeElementRepository = typeElementRepository;
    }

    @Transactional
    public TypeElement saveTypeElement(TypeElement typeElement) {
        LOG.info("Guardando un nuevo tipo de elemento en la base de datos: {}", typeElement);
        return typeElementRepository.save(typeElement);
    }

    @Transactional(readOnly = true)
    public List<TypeElement> findAllTypeElements() {
        LOG.info("Recuperando todos los tipos de elementos de la base de datos");
        return typeElementRepository.findAll();
    }

    @Transactional(readOnly = true)
    public TypeElement findTypeElementById(Long id) {
        LOG.info("Recuperando un tipo de elemento con ID: {}", id);
        return typeElementRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Tipo de elemento no encontrado con ID: " + id));
    }

    @Transactional
    public void deleteTypeElement(Long id) {
        LOG.info("Eliminando tipo de elemento con ID: {}", id);
        typeElementRepository.deleteById(id);
    }

    @Transactional
    public TypeElement updateTypeElement(Long id, TypeElement newTypeElementData) {
        LOG.info("Actualizando tipo de elemento en la base de datos con ID: {}", id);
        return typeElementRepository.findById(id)
            .map(existingTypeElement -> {
                existingTypeElement.setTypeElementName(newTypeElementData.getTypeElementName());
                // Aquí puedes actualizar otros campos si es necesario
                return typeElementRepository.save(existingTypeElement);
            }).orElseThrow(() -> new RuntimeException("Tipo de elemento no encontrado con ID: " + id));
    }
}
