package com.sss.garage.model.classification;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.league.League;
import com.sss.garage.model.team.Team;
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

    @ManyToOne
    private Team team;

    private Integer points;

    private Integer position = 0;

    private Integer positionCount = 0;

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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getPositionCount() {
        return positionCount;
    }

    public void setPositionCount(Integer positionCount) {
        this.positionCount = positionCount;
    }
}
