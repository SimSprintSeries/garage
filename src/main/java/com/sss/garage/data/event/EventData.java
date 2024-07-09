package com.sss.garage.data.event;

import java.util.Set;

import com.sss.garage.data.league.LeagueData;
import com.sss.garage.data.presence.PresenceData;
import com.sss.garage.data.race.RaceData;
import com.sss.garage.data.track.TrackData;

public class EventData {
    private Long id;
    private String name;
    private String displayText;
    private LeagueData league;
    private TrackData track;
    private Set<RaceData> races;
    private Set<PresenceData> presences;
    private Boolean activeForPresence;

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

    public LeagueData getLeague() {
        return league;
    }

    public void setLeague(final LeagueData league) {
        this.league = league;
    }

    public TrackData getTrack() {
        return track;
    }

    public void setTrack(final TrackData track) {
        this.track = track;
    }

    public Set<RaceData> getRaces() {
        return races;
    }

    public void setRaces(final Set<RaceData> races) {
        this.races = races;
    }

    public Set<PresenceData> getPresences() {
        return presences;
    }

    public void setPresences(final Set<PresenceData> presences) {
        this.presences = presences;
    }

    public Boolean getActiveForPresence() {
        return activeForPresence;
    }

    public void setActiveForPresence(final Boolean activeForPresence) {
        this.activeForPresence = activeForPresence;
    }
}
