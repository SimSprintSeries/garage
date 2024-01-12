package com.sss.garage.model.classification;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.league.League;
import jakarta.persistence.*;

@Entity
public class Classification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private League league;

    private Integer points;

    private Integer p1Count;

    private Integer p2Count;

    private Integer p3Count;

    private Integer p4Count;

    private Integer p5Count;

    private Integer p6Count;

    private Integer p7Count;

    private Integer p8Count;

    private Integer p9Count;

    private Integer p10Count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getP1Count() {
        return p1Count;
    }

    public void setP1Count(Integer p1Count) {
        this.p1Count = p1Count;
    }

    public Integer getP2Count() {
        return p2Count;
    }

    public void setP2Count(Integer p2Count) {
        this.p2Count = p2Count;
    }

    public Integer getP3Count() {
        return p3Count;
    }

    public void setP3Count(Integer p3Count) {
        this.p3Count = p3Count;
    }

    public Integer getP4Count() {
        return p4Count;
    }

    public void setP4Count(Integer p4Count) {
        this.p4Count = p4Count;
    }

    public Integer getP5Count() {
        return p5Count;
    }

    public void setP5Count(Integer p5Count) {
        this.p5Count = p5Count;
    }

    public Integer getP6Count() {
        return p6Count;
    }

    public void setP6Count(Integer p6Count) {
        this.p6Count = p6Count;
    }

    public Integer getP7Count() {
        return p7Count;
    }

    public void setP7Count(Integer p7Count) {
        this.p7Count = p7Count;
    }

    public Integer getP8Count() {
        return p8Count;
    }

    public void setP8Count(Integer p8Count) {
        this.p8Count = p8Count;
    }

    public Integer getP9Count() {
        return p9Count;
    }

    public void setP9Count(Integer p9Count) {
        this.p9Count = p9Count;
    }

    public Integer getP10Count() {
        return p10Count;
    }

    public void setP10Count(Integer p10Count) {
        this.p10Count = p10Count;
    }
}
