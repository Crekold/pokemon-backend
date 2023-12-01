package com.backend.pokemon.model;

import jakarta.persistence.*;

@Entity
@Table(name = "team_stats")
public class TeamStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_stats_id")
    private Long teamStatsId;

    @Column(name = "hp_prom", nullable = false)
    private int hpProm;

    @Column(name = "attack_prom", nullable = false)
    private int attackProm;

    @Column(name = "defense_prom", nullable = false)
    private int defenseProm;

    @Column(name = "SA_prom", nullable = false)
    private int saProm;

    @Column(name = "SE_prom", nullable = false)
    private int seProm;

    @ManyToOne
    @JoinColumn(name = "team_team_id")
    private Team team;

    // Constructors, getters, and setters
    public TeamStats() {
    }

    public TeamStats(int hpProm, int attackProm, int defenseProm, int saProm, int seProm, Team team) {
        this.hpProm = hpProm;
        this.attackProm = attackProm;
        this.defenseProm = defenseProm;
        this.saProm = saProm;
        this.seProm = seProm;
        this.team = team;
    }

    // Getters and setters
    public Long getTeamStatsId() {
        return teamStatsId;
    }

    public void setTeamStatsId(Long teamStatsId) {
        this.teamStatsId = teamStatsId;
    }

    public int getHpProm() {
        return hpProm;
    }

    public void setHpProm(int hpProm) {
        this.hpProm = hpProm;
    }

    public int getAttackProm() {
        return attackProm;
    }

    public void setAttackProm(int attackProm) {
        this.attackProm = attackProm;
    }

    public int getDefenseProm() {
        return defenseProm;
    }

    public void setDefenseProm(int defenseProm) {
        this.defenseProm = defenseProm;
    }

    public int getSaProm() {
        return saProm;
    }

    public void setSaProm(int saProm) {
        this.saProm = saProm;
    }

    public int getSeProm() {
        return seProm;
    }

    public void setSeProm(int seProm) {
        this.seProm = seProm;
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
        return "TeamStats{" +
                "teamStatsId=" + teamStatsId +
                ", hpProm=" + hpProm +
                ", attackProm=" + attackProm +
                ", defenseProm=" + defenseProm +
                ", saProm=" + saProm +
                ", seProm=" + seProm +
                ", team=" + team +
                '}';
    }
}
