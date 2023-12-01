package com.backend.pokemon.dto;

public class TeamStatsDTO {

    private Long teamStatsId;
    private int hpProm;
    private int attackProm;
    private int defenseProm;
    private int saProm;
    private int seProm;
    private TeamDTO team;

    // Constructors
    public TeamStatsDTO() {
    }

    public TeamStatsDTO(Long teamStatsId, int hpProm, int attackProm, int defenseProm, int saProm, int seProm, TeamDTO team) {
        this.teamStatsId = teamStatsId;
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

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }
}
