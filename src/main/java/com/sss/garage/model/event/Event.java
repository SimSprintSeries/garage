package com.sss.garage.model.role;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

import com.sss.garage.model.league.League;

@Entity
public class Event {

    @Id
    private Long id;
    private String name;
    private LocalDateTime startTime;

    private League league;

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
}
