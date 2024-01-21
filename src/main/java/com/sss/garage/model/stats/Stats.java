package com.sss.garage.model.stats;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Stats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer starts;

    private Integer wins;

    private Integer podiums;

    private Integer top10;

    private Integer dnfs;

    private Integer dsqs;

    private Integer fastestLaps;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStarts() {
        return starts;
    }

    public void setStarts(Integer starts) {
        this.starts = starts;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getPodiums() {
        return podiums;
    }

    public void setPodiums(Integer podiums) {
        this.podiums = podiums;
    }

    public Integer getTop10() {
        return top10;
    }

    public void setTop10(Integer top10) {
        this.top10 = top10;
    }

    public Integer getDnfs() {
        return dnfs;
    }

    public void setDnfs(Integer dnfs) {
        this.dnfs = dnfs;
    }

    public Integer getDsqs() {
        return dsqs;
    }

    public void setDsqs(Integer dsqs) {
        this.dsqs = dsqs;
    }

    public Integer getFastestLaps() {
        return fastestLaps;
    }

    public void setFastestLaps(Integer fastestLaps) {
        this.fastestLaps = fastestLaps;
    }
}
