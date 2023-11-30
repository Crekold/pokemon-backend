package com.backend.pokemon.dto;

public class UserDTO {

    private String userId;
    private String nickname;

    // Constructores, getters y setters

    public UserDTO() {
    }

    public UserDTO(String userId, String nickname) {
        this.userId = userId;
        this.nickname = nickname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
