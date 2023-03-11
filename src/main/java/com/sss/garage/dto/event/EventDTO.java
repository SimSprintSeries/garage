package com.sss.garage.dto.event;

import com.sss.garage.dto.league.LeagueDTO;

public class EventDTO {
    private Long id;
    private String displayText;
    private String startDate;
    private LeagueDTO league;

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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(final String startDate) {
        this.startDate = startDate;
    }

    public LeagueDTO getLeague() {
        return league;
    }

    public void setLeague(final LeagueDTO league) {
        this.league = league;
    }
}
