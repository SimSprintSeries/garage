package com.sss.garage.dto.league;

import java.util.List;

import com.sss.garage.dto.event.EventDTO;

public class DetailedLeagueDTO extends LeagueDTO {
    List<EventDTO> events;

    public List<EventDTO> getEvents() {
        return events;
    }

    public void setEvents(final List<EventDTO> events) {
        this.events = events;
    }
}
