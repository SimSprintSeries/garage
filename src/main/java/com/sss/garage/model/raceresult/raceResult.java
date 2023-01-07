package com.sss.garage.model.role;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.event.Event;
import com.sss.garage.model.league.League;


@Entity
public class RaceResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int finishPosition;
    private boolean polePosition;
    private boolean dnf;
    private boolean fastestLap;

    private Driver driver;
    private Event event;
    private League league;


    public long getId() {
        return id;
    }
    public void setId(final long id) {
        this.id = id;
    }

    public int getFinishPosition() {
        return finishPosition;
    }
    public void setFinishPosition(final int finishPosition) {
        this.finishPosition = finishPosition;
    }

    public boolean getPolePosition() {
        return polePosition;
    }
    public void setPolePosition(final boolean polePosition) {
        this.polePosition = polePosition;
    }

    public boolean getDnf() {
        return dnf;
    }
    public void setDnf(final boolean dnf) {
        this.dnf = dnf;
    }

    public boolean getFastestLap() {
        return fastestLap;
    }
    public void setFastestLap(final boolean fastestLap) {
        this.fastestLap = fastestLap;
    }

    public Driver getDriver() {
        return driver;
    }
    public void setDriver(final Driver driver) {
        this.driver = driver;
    }

    public Event getEvent() {
        return event;
    }
    public void setEvent(final Event event) {
        this.event = event;
    }

    public League getLeague() {
        return league;
    }
    public void setLeague(final League league) {
        this.league = league;
    }
}
