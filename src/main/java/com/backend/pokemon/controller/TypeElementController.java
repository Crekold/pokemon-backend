package com.backend.pokemon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.backend.pokemon.model.TypeElement;
import com.backend.pokemon.service.TypeElementService;

import java.util.List;

@RestController
@RequestMapping("/typeElement")
public class TypeElementController {

    @Autowired
    private TypeElementService typeElementService;

    @PostMapping
    public TypeElement createTypeElement(@RequestBody TypeElement typeElement) {
        return typeElementService.saveTypeElement(typeElement);
    }

    @GetMapping
    public List<TypeElement> getAllTypeElements() {
        return typeElementService.getAllTypeElements();
    }

    @GetMapping("/{id}")
    public TypeElement getTypeElementById(@PathVariable Long id) {
        return typeElementService.getTypeElementById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteTypeElement(@PathVariable Long id) {
        typeElementService.deleteTypeElement(id);
    }

    // Endpoint para actualizar un TypeElement
}
