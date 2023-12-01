package com.backend.pokemon.repository;

import com.backend.pokemon.model.TeamSuggestion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamSuggestionRepository extends JpaRepository<TeamSuggestion, Long> {
    TeamSuggestion findByTeam_TeamId(Long teamId);
}