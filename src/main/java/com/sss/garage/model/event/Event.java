package com.sss.garage.model.event;

import java.util.Date;
import java.util.Set;

import com.sss.garage.model.presence.Presence;
import com.sss.garage.model.track.Track;
import jakarta.persistence.*;

import com.sss.garage.model.league.League;
import com.sss.garage.model.race.Race;

import org.jetbrains.annotations.NotNull;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @ManyToOne
    private League league;

    private String sprite;

    @OneToMany(mappedBy = "event")
    private Set<Race> races;

    @ManyToOne
    private Track track;

    private Boolean activeForPresence = false;

    @OneToMany(mappedBy = "event")
    private Set<Presence> presences;

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

    public League getLeague() {
        return league;
    }

    public void setLeague(final League league) {
        this.league = league;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(final String sprite) {
        this.sprite = sprite;
    }

    public Set<Race> getRaces() {
        return races;
    }

    public void setRaces(final Set<Race> races) {
        this.races = races;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(final Track track) {
        this.track = track;
    }

    public Boolean getActiveForPresence() {
        return activeForPresence;
    }

    public void setActiveForPresence(Boolean activeForPresence) {
        this.activeForPresence = activeForPresence;
    }

    public Set<Presence> getPresences() {
        return presences;
    }

    public void setPresences(Set<Presence> presences) {
        this.presences = presences;
    }
}
