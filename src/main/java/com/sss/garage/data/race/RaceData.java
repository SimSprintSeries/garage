package com.sss.garage.data.race;

import com.sss.garage.data.presence.PresenceData;
import com.sss.garage.data.split.SplitData;
import com.sss.garage.data.track.TrackData;

import java.util.Date;
import java.util.Set;

public class RaceData {
    private Long id;
    private SplitData split;
    private String displayText;
    private Boolean activeForPresence;
    private Set<PresenceData> presences;
    private String name;
    private TrackData track;
    private Long eventId;

    public SplitData getSplit() {
        return split;
    }

    public void setSplit(final SplitData split) {
        this.split = split;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(final String displayText) {
        this.displayText = displayText;
    }

    public Boolean getActiveForPresence() {
        return activeForPresence;
    }

    public void setActiveForPresence(final Boolean activeForPresence) {
        this.activeForPresence = activeForPresence;
    }

    public Set<PresenceData> getPresences() {
        return presences;
    }

    public void setPresences(final Set<PresenceData> presences) {
        this.presences = presences;
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

    public TrackData getTrack() {
        return track;
    }

    public void setTrack(final TrackData track) {
        this.track = track;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(final Long eventId) {
        this.eventId = eventId;
    }
}
