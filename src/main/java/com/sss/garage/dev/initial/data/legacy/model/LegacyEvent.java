package com.sss.garage.dev.initial.data.legacy.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LegacyEvent {
    public Integer id;
    public String name;
    public String country;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date starts;
    public Long league_id;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public Date getStarts() {
        return starts;
    }

    public void setStarts(final Date starts) {
        this.starts = starts;
    }

    public Long getLeague_id() {
        return league_id;
    }

    public void setLeague_id(final Long league_id) {
        this.league_id = league_id;
    }
}
