package com.backend.pokemon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.pokemon.model.TypeElement;
import com.backend.pokemon.repository.TypeElementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TypeElementService {

    @Autowired
    private TypeElementRepository typeElementRepository;

    public TypeElement saveTypeElement(TypeElement typeElement) {
        return typeElementRepository.save(typeElement);
    }

    public List<TypeElement> getAllTypeElements() {
        return typeElementRepository.findAll();
    }

    public Optional<TypeElement> getTypeElementById(Long id) {
        return typeElementRepository.findById(id);
    }

    public void deleteTypeElement(Long id) {
        typeElementRepository.deleteById(id);
    }

    // Método para actualizar un TypeElement existente
}
