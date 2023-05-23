package com.sss.garage.dto.league;

import java.util.List;

import com.sss.garage.dto.event.EventDTO;
import com.sss.garage.dto.split.SplitDTO;

public class DetailedLeagueDTO extends LeagueDTO {
    private List<EventDTO> events;

    private List<SplitDTO> splits;

    private Boolean active;

    private String discordGroupId;

    public List<EventDTO> getEvents() {
        return events;
    }

    public void setEvents(final List<EventDTO> events) {
        this.events = events;
    }

    public List<SplitDTO> getSplits() {
        return splits;
    }

    public void setSplits(final List<SplitDTO> splits) {
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
