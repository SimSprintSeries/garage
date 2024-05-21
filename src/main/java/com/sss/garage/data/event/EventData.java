package com.sss.garage.data.event;

import java.util.Date;
import java.util.Set;

import com.sss.garage.data.league.LeagueData;
import com.sss.garage.data.race.RaceData;
import com.sss.garage.data.track.TrackData;

public class EventData {
    private Long id;
    private String displayText;
    private Date startDate;
    private LeagueData league;
    private TrackData track;
    private Boolean activeForPresence;
    private Set<RaceData> races;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(final String displayText) {
        this.displayText = displayText;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
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

    public Boolean getActiveForPresence() {
        return activeForPresence;
    }

    public void setActiveForPresence(final Boolean activeForPresence) {
        this.activeForPresence = activeForPresence;
    }

    public Set<RaceData> getRaces() {
        return races;
    }

    public void setRaces(final Set<RaceData> races) {
        this.races = races;
    }
}
