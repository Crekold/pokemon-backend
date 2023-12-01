package com.backend.pokemon.repository;

import com.backend.pokemon.model.TeamStats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamStatsRepository extends JpaRepository<TeamStats, Long> {
    TeamStats findByTeam_TeamId(Long teamId);
}