package com.backend.pokemon.dto;

import java.util.List;

public class PokemonApiDto {
    private int id;
    private String name;
    private Sprites sprites;
    private List<TypeElementDto> types;

    // Getters y setters para id, name y sprites
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public List<TypeElementDto> getTypes() {
        return types;
    }

    public void setTypes(List<TypeElementDto> types) {
        this.types = types;
    }

    // Static nested class Sprites
    public static class Sprites {
        private String front_default;

        // Getters y setters para front_default
        public String getFront_default() {
            return front_default;
        }

        public void setFront_default(String front_default) {
            this.front_default = front_default;
        }
    }

    // Static nested class TypeElementDto
    public static class TypeElementDto {
        private Type type;

        // Getters y setters para type
        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }

        // Static nested class Type
        public static class Type {
            private String name;

            // Getters y setters para name
            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
