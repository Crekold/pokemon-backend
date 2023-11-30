package com.backend.pokemon.dto;

public class TypeElementDTO {

    private Long typeElementId;
    private String typeElementName;

    public TypeElementDTO() {
    }

    public TypeElementDTO(Long typeElementId, String typeElementName) {
        this.typeElementId = typeElementId;
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
}
