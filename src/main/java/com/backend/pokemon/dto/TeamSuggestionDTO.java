package com.backend.pokemon.dto;

public class TeamSuggestionDTO {
    
    private Long teamSuggestionId;
    private String suggestionExplanation;
    private TeamDTO team;

    // Constructors
    public TeamSuggestionDTO() {
    }

    public TeamSuggestionDTO(Long teamSuggestionId, String suggestionExplanation, TeamDTO team) {
        this.teamSuggestionId = teamSuggestionId;
        this.suggestionExplanation = suggestionExplanation;
        this.team = team;
    }

    // Getters and setters
    public Long getTeamSuggestionId() {
        return teamSuggestionId;
    }

    public void setTeamSuggestionId(Long teamSuggestionId) {
        this.teamSuggestionId = teamSuggestionId;
    }

    public String getSuggestionExplanation() {
        return suggestionExplanation;
    }

    public void setSuggestionExplanation(String suggestionExplanation) {
        this.suggestionExplanation = suggestionExplanation;
    }

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }
}
