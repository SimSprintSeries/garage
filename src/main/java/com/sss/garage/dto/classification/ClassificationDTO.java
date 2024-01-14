package com.sss.garage.dto.classification;

import com.sss.garage.dto.driver.SimpleDriverDTO;
import com.sss.garage.dto.league.LeagueDTO;
import com.sss.garage.dto.team.TeamDTO;

public class ClassificationDTO {
    private Long id;

    private SimpleDriverDTO driver;

    private LeagueDTO league;

    private TeamDTO team;

    private Integer points;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SimpleDriverDTO getDriver() {
        return driver;
    }

    public void setDriver(SimpleDriverDTO driver) {
        this.driver = driver;
    }

    public LeagueDTO getLeague() {
        return league;
    }

    public void setLeague(LeagueDTO league) {
        this.league = league;
    }

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
