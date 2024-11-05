package com.sss.garage.dto.race;

import com.sss.garage.dto.split.SplitDTO;
import com.sss.garage.dto.track.TrackDTO;

public class RaceDTO {
    private Long id;
    private SplitDTO split;
    private String name;
    private String displayText;
    private TrackDTO track;
    private Long eventId;

    public SplitDTO getSplit() {
        return split;
    }

    public void setSplit(final SplitDTO split) {
        this.split = split;
    }

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

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(final String displayText) {
        this.displayText = displayText;
    }

    public TrackDTO getTrack() {
        return track;
    }

    public void setTrack(final TrackDTO track) {
        this.track = track;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(final Long eventId) {
        this.eventId = eventId;
    }
}
