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
    private Long id;
    private Integer finishPosition;
    private Boolean polePosition;
    private Boolean dnf;
    private Boolean fastestLap;

    private Driver driver;
    private Event event;


    public Long getId() {
        return id;
    }
    public void setId(final Long id) {
        this.id = id;
    }

    public Integer getFinishPosition() {
        return finishPosition;
    }
    public void setFinishPosition(final Integer finishPosition) {
        this.finishPosition = finishPosition;
    }

    public Boolean getPolePosition() {
        return polePosition;
    }
    public void setPolePosition(final Boolean polePosition) {
        this.polePosition = polePosition;
    }

    public Boolean getDnf() {
        return dnf;
    }
    public void setDnf(final Boolean dnf) {
        this.dnf = dnf;
    }

    public Boolean getFastestLap() {
        return fastestLap;
    }
    public void setFastestLap(final Boolean fastestLap) {
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
}
