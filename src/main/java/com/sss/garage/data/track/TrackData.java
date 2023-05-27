package com.sss.garage.data.track;

import com.sss.garage.data.event.EventData;

import java.util.Set;

public class TrackData {

    private String name;

    private String country;

    private Set<EventData> events;


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

    public Set<EventData> getEvents() {
        return events;
    }

    public void setEvents(final Set<EventData> events) {
        this.events = events;
    }
}
