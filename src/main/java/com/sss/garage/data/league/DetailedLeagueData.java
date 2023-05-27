package com.sss.garage.data.league;

import java.util.List;

import com.sss.garage.data.event.EventData;
import com.sss.garage.data.split.SplitData;

public class DetailedLeagueData extends LeagueData {
    private List<EventData> events;

    private List<SplitData> splits;

    private Boolean active;

    private String discordGroupId;

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

    public List<SplitData> getSplits() {
        return splits;
    }

    public void setSplits(final List<SplitData> splits) {
        this.splits = splits;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(final Boolean active) {
        this.active = active;
    }

    public String getDiscordGroupId() {
        return discordGroupId;
    }

    public void setDiscordGroupId(final String discordGroupId) {
        this.discordGroupId = discordGroupId;
    }
}
