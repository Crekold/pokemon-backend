package com.backend.pokemon.dto;

public class TeamDTO {

    private Long teamId;
    private String teamName;
    private UserDTO user;

    public TeamDTO() {
    }

    public TeamDTO(Long teamId, String teamName, UserDTO user) {
        this.teamId = teamId;
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}