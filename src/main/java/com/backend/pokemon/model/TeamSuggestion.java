package com.backend.pokemon.model;

import jakarta.persistence.*;

@Entity
@Table(name = "team_suggestion")
public class TeamSuggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_suggestion_id")
    private Long teamSuggestionId;

    @Column(name = "suggestion_explanation", nullable = false, columnDefinition = "TEXT")
    private String suggestionExplanation;

    @ManyToOne
    @JoinColumn(name = "team_team_id")
    private Team team;

    // Constructors, getters, and setters
    public TeamSuggestion() {
    }

    public TeamSuggestion(String suggestionExplanation, Team team) {
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    // toString
    @Override
    public String toString() {
        return "TeamSuggestion{" +
                "teamSuggestionId=" + teamSuggestionId +
                ", suggestionExplanation='" + suggestionExplanation + '\'' +
                ", team=" + team +
                '}';
    }
}
