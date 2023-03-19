package com.sss.garage.dto.split;

import com.sss.garage.dto.league.LeagueDTO;

public class SplitDTO {
    private Long id;
    private String name;
    private String displayText;
    private LeagueDTO league;

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
}
