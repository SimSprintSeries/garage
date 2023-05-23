package com.sss.garage.data.event;

import java.util.Date;

import com.sss.garage.data.league.LeagueData;
import com.sss.garage.data.track.TrackData;

public class EventData {
    private Long id;
    private String displayText;
    private Date startDate;
    private LeagueData league;
    private TrackData track;

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
}
