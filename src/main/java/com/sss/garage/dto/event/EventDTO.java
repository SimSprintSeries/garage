package com.sss.garage.dto.event;

import com.sss.garage.dto.league.LeagueDTO;
import com.sss.garage.dto.race.RaceDTO;
import com.sss.garage.dto.track.TrackDTO;

import java.util.Set;

public class EventDTO {
    private Long id;
    private String displayText;
    private LeagueDTO league;
    private TrackDTO track;
    private Set<RaceDTO> races;

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

    public LeagueDTO getLeague() {
        return league;
    }

    public void setLeague(final LeagueDTO league) {
        this.league = league;
    }

    public TrackDTO getTrack() {
        return track;
    }

    public void setTrack(final TrackDTO track) {
        this.track = track;
    }

    public Set<RaceDTO> getRaces() {
        return races;
    }

    public void setRaces(final Set<RaceDTO> races) {
        this.races = races;
    }
}
