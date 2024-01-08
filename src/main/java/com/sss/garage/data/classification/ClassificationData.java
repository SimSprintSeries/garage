package com.sss.garage.data.classification;

import com.sss.garage.data.driver.DriverData;
import com.sss.garage.data.league.LeagueData;

public class ClassificationData {
    private Long id;

    private DriverData driver;

    private LeagueData league;

    private Integer points;

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

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
