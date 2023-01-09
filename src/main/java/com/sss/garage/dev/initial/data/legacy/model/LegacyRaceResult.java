package com.sss.garage.dev.initial.data.legacy.model;

public class LegacyRaceResult {
    public Long position;
    public Boolean dnf;
    public Boolean dsq;
    public Boolean pole;
    public Boolean lap;
    public Long driver;
    public String comment;
    public Long eventid;
    public Long raceid;
    public Long relatedleague;
    public String racename;

    public Long getId() {
        return position;
    }

    public void setId(final Long position) {
        this.position = position;
    }

    public Boolean getDnf() {
        return dnf;
    }

    public void setDnf(final Boolean dnf) {
        this.dnf = dnf;
    }

    public Boolean getDsq() {
        return dsq;
    }

    public void setDsq(final Boolean dsq) {
        this.dsq = dsq;
    }

    public Boolean getPole() {
        return pole;
    }

    public void setPole(final Boolean pole) {
        this.pole = pole;
    }

    public Boolean getLap() {
        return lap;
    }

    public void setLap(final Boolean lap) {
        this.lap = lap;
    }

    public Long getDriver() {
        return driver;
    }

    public void setDriver(final Long driver) {
        this.driver = driver;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public Long getEventId() {
        return eventid;
    }

    public void setEventId(final Long eventId) {
        this.eventid = eventId;
    }

    public Long getRaceId() {
        return raceid;
    }

    public void setRaceId(final Long raceId) {
        this.raceid = raceId;
    }

    public Long getRelatedLeague() {
        return relatedleague;
    }

    public void setRelatedLeague(final Long relatedLeague) {
        this.relatedleague = relatedLeague;
    }

    public String getRacename() {
        return racename;
    }

    public void setRacename(final String racename) {
        this.racename = racename;
    }
}
