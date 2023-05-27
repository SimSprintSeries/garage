package com.sss.garage.dto.track;

import com.sss.garage.dto.event.EventDTO;
import com.sss.garage.dto.race.RaceDTO;

import java.util.Set;

public class TrackDTO {

    private String name;

    private String country;

    private Set<EventDTO> events;


    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public Set<EventDTO> getEvents() {
        return events;
    }

    public void setEvents(final Set<EventDTO> events) {
        this.events = events;
    }
}
