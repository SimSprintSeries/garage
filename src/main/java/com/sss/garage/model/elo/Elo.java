package com.sss.garage.model.role;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.game.Game;

@Entity
public class Elo {

    @Id
    private Long id;
    private Integer eloF12019;
    private Integer eloF12020;
    private Integer eloF12021;
    private Integer eloF122;
    private Integer eloAC;
    private Integer eloACC;
    private Integer eloF1;

    private Driver driver;
    private Game game;


    public Long getId() {
        return id;
    }
    public void setId(final Long id) {
        this.id = id;
    }

    public Integer getEloF12019() {
        return eloF12019;
    }
    public void setEloF12019(final Integer eloF12019) {
        this.eloF12019 = eloF12019;
    }

    public Integer getEloF12020() {
        return eloF12020;
    }
    public void setEloF12020(final Integer eloF12020) {
        this.eloF12020 = eloF12020;
    }

    public Integer getEloF12021() {
        return eloF12021;
    }
    public void setEloF12021(final Integer eloF12021) {
        this.eloF12021 = eloF12021;
    }

    public Integer getEloF122() {
        return eloF122;
    }
    public void setEloF122(final Integer eloF122) {
        this.eloF122 = eloF122;
    }

    public Integer getEloAC() {
        return eloAC;
    }
    public void setEloAC(final Integer eloAC) {
        this.eloAC = eloAC;
    }

    public Integer getEloACC() {
        return eloACC;
    }
    public void setEloACC(final Integer eloACC) {
        this.eloACC = eloACC;
    }

    public Integer getEloF1() {
        return eloF1;
    }
    public void setEloF1(final Integer eloF1) {
        this.eloF1 = eloF1;
    }

    public Driver getDriver() {
        return driver;
    }
    public void setDriver(final Driver driver) {
        this.driver = driver;
    }

    public Game getGame() {
        return game;
    }
    public void setGame(final Game game) {
        this.game = game;
    }
}
