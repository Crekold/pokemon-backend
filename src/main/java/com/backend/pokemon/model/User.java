package com.backend.pokemon.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    
    @Id
    @Column(name = "user_id")
    private String userId;
    
    @Column(name = "nickname")
    private String nickname;

    // Constructores, getters y setters
    public User() {
    }

    public User(String userId, String nickname) {
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

    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
