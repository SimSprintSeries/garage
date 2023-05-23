package com.sss.garage.dto.split;

import com.sss.garage.dto.driver.DetailedDriverDTO;
import com.sss.garage.dto.league.LeagueDTO;

import java.util.Set;

public class SplitDTO {
    private Long id;
    private String name;
    private String displayText;
    private LeagueDTO league;
    private Set<DetailedDriverDTO> drivers;

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

    public LeagueDTO getLeague() {
        return league;
    }

    public void setLeague(final LeagueDTO league) {
        this.league = league;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(final String displayText) {
        this.displayText = displayText;
    }

    public Set<DetailedDriverDTO> getDrivers() {
        return drivers;
    }

    public void setDrivers(final Set<DetailedDriverDTO> drivers) {
        this.drivers = drivers;
    }
}
