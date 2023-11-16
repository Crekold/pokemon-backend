package com.backend.pokemon.model;

import jakarta.persistence.*;

@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long teamId;
    
    @Column(name = "team_name")
    private String teamName;

    @ManyToOne()
    @JoinColumn(name = "user_user_id")
    private User user;

    // Constructores, getters y setters
    public Team() {
    }

    public Team(String teamName, User user) {
        this.teamName = teamName;
        this.user = user;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", user=" + (user != null ? user.getUserId() : "null") +
                '}';
    }
}
