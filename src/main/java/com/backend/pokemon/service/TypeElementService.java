package com.backend.pokemon.service;

import com.backend.pokemon.model.TypeElement;
import com.backend.pokemon.repository.TypeElementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TypeElementService {

    private static final Logger LOG = LoggerFactory.getLogger(TypeElementService.class);

    private final TypeElementRepository typeElementRepository;

    @Autowired
    public TypeElementService(TypeElementRepository typeElementRepository) {
        this.typeElementRepository = typeElementRepository;
    }

    @Transactional
    public TypeElement saveOrGetTypeElement(String typeName) {
        // Intenta encontrar el TypeElement por nombre, si no existe, crea y guarda uno nuevo
        return typeElementRepository.findByTypeElementName(typeName)
                .orElseGet(() -> {
                    TypeElement newTypeElement = new TypeElement();
                    newTypeElement.setTypeElementName(typeName);
                    LOG.info("Guardando un nuevo TypeElement en la base de datos: {}", newTypeElement);
                    return typeElementRepository.save(newTypeElement);
                });
    }

    // ...otros métodos del servicio...
}
