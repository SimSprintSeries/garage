package com.sss.garage.data.league;

import java.util.List;

import com.sss.garage.data.event.EventData;

public class DetailedLeagueData extends LeagueData {
    private List<EventData> events;

    public DetailedLeagueData() {
    }

    public DetailedLeagueData(final LeagueData leagueData) {
        this.setId(leagueData.getId());
        this.setGame(leagueData.getGame());
        this.setName(leagueData.getName());
        this.setPlatform(leagueData.getPlatform());
    }

    public List<EventData> getEvents() {
        return events;
    }

    public void setEvents(final List<EventData> events) {
        this.events = events;
    }
}
