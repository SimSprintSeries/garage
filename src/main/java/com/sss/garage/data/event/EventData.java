package com.sss.garage.data.event;

import java.util.Date;

import com.sss.garage.data.league.LeagueData;

public class EventData {
    private Long id;
    private String displayText;
    private Date startDate;
    private LeagueData league;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(final String displayText) {
        this.displayText = displayText;
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
