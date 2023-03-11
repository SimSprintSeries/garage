package com.sss.garage.data.split;

import com.sss.garage.data.league.LeagueData;

public class SplitData {
    private Long id;
    private String name;
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

    public LeagueData getLeague() {
        return league;
    }

    public void setLeague(final LeagueData league) {
        this.league = league;
    }
}
