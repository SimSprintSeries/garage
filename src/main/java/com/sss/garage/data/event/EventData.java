package com.sss.garage.data.event;

import java.util.Date;

import com.sss.garage.data.league.LeagueData;

public class EventData {
    private Long id;
    private String name;
    private Date startDate;
    private LeagueData league;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    public LeagueData getLeague() {
        return league;
    }

    public void setLeague(final LeagueData league) {
        this.league = league;
    }
}
