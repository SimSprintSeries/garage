package com.sss.garage.model.event;

import com.sss.garage.model.raceresult.RaceResult;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

import com.sss.garage.model.league.League;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDateTime startTime;

    @ManyToOne
    private League league;

    @OneToMany(mappedBy = "event")
    private Set<RaceResult> raceResults;

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

    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(final LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public League getLeague() {
        return league;
    }
    public void setLeague(final League league) {
        this.league = league;
    }

    public Set<RaceResult> getRaceResults() {
        return raceResults;
    }
    public void setRaceResults(final Set<RaceResult> raceResults) {
        this.raceResults = raceResults;
    }
}
