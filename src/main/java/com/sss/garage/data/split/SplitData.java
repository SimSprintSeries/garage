package com.sss.garage.data.split;

import com.sss.garage.data.driver.DriverData;
import com.sss.garage.data.league.LeagueData;

import java.util.Set;

public class SplitData {
    private Long id;
    private String name;
    private String displayText;
    private LeagueData league;
    private Set<DriverData> drivers;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public LeagueData getLeague() {
        return league;
    }

    public void setLeague(final LeagueData league) {
        this.league = league;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(final String displayText) {
        this.displayText = displayText;
    }

    public Set<DriverData> getDrivers() {
        return drivers;
    }

    public void setDrivers(final Set<DriverData> drivers) {
        this.drivers = drivers;
    }
}
