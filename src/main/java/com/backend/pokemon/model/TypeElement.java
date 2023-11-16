package com.backend.pokemon.model;

import jakarta.persistence.*;

@Entity
@Table(name = "type_element")
public class TypeElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_element_id")
    private Long typeElementId;
    
    @Column(name = "type_element_name")
    private String typeElementName;

    // Constructores, getters y setters
    public TypeElement() {
    }

    public TypeElement(String typeElementName) {
        this.typeElementName = typeElementName;
    }

    public Long getTypeElementId() {
        return typeElementId;
    }

    public void setTypeElementId(Long typeElementId) {
        this.typeElementId = typeElementId;
    }

    public String getTypeElementName() {
        return typeElementName;
    }

    public void setTypeElementName(String typeElementName) {
        this.typeElementName = typeElementName;
    }

    @Override
    public String toString() {
        return "TypeElement{" +
                "typeElementId=" + typeElementId +
                ", typeElementName='" + typeElementName + '\'' +
                '}';
    }
}
