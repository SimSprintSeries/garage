package com.sss.garage.data.classification;

import com.sss.garage.data.driver.DriverData;
import com.sss.garage.data.league.LeagueData;
import com.sss.garage.data.raceresult.RaceResultData;
import com.sss.garage.data.team.TeamData;

import java.util.List;

public class ClassificationData {
    private Long id;

    private DriverData driver;

    private LeagueData league;

    private TeamData team;

    private Integer points;

    private List<RaceResultData> raceResults;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DriverData getDriver() {
        return driver;
    }

    public void setDriver(DriverData driver) {
        this.driver = driver;
    }

    public LeagueData getLeague() {
        return league;
    }

    public void setLeague(LeagueData league) {
        this.league = league;
    }

    public TeamData getTeam() {
        return team;
    }

    public void setTeam(TeamData team) {
        this.team = team;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public List<RaceResultData> getRaceResults() {
        return raceResults;
    }

    public void setRaceResults(final List<RaceResultData> raceResults) {
        this.raceResults = raceResults;
    }
}
