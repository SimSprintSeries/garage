package com.sss.garage.model.raceresult;

import com.sss.garage.model.race.Race;
import com.sss.garage.model.team.Team;
import jakarta.persistence.*;

import com.sss.garage.model.driver.Driver;
import org.jetbrains.annotations.NotNull;


@Entity
public class RaceResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer finishPosition;

    @NotNull
    private Boolean polePosition;

    @NotNull
    private Boolean dnf;

    @NotNull
    private Boolean dsq;

    @NotNull
    private Boolean fastestLap;

    @NotNull
    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Race race;

    @ManyToOne
    private Team team;

    private Integer pointsForPosition;

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

    public Race getRace() {
        return race;
    }

    public void setRace(final Race race) {
        this.race = race;
    }

    @NotNull
    public Boolean getDsq() {
        return dsq;
    }

    public void setDsq(@NotNull final Boolean dsq) {
        this.dsq = dsq;
    }

    public Integer getPointsForPosition() {
        return pointsForPosition;
    }

    public void setPointsForPosition(Integer pointsForPosition) {
        this.pointsForPosition = pointsForPosition;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
