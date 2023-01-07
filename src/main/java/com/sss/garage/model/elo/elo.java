package com.sss.garage.model.role;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import com.sss.garage.model.driver.Driver;

@Entity
public class Elo {

    @Id
    private long driverId;
    private int eloF12019;
    private int eloF12020;
    private int eloF12021;
    private int eloF122;
    private int eloAC;
    private int eloACC;
    private int eloF1;

    private Driver driver;


    public long getDriverId() {
        return driverId;
    }
    public void setDriverId(final long driverId) {
        this.driverId = driverId;
    }

    public int getEloF12019() {
        return eloF12019;
    }
    public void setEloF12019(final int eloF12019) {
        this.eloF12019 = eloF12019;
    }

    public int getEloF12020() {
        return eloF12020;
    }
    public void setEloF12020(final int eloF12020) {
        this.eloF12020 = eloF12020;
    }

    public int getEloF12021() {
        return eloF12021;
    }
    public void setEloF12021(final int eloF12021) {
        this.eloF12021 = eloF12021;
    }

    public int getEloF122() {
        return eloF122;
    }
    public void setEloF122(final int eloF122) {
        this.eloF122 = eloF122;
    }

    public int getEloAC() {
        return eloAC;
    }
    public void setEloAC(final int eloAC) {
        this.eloAC = eloAC;
    }

    public int getEloACC() {
        return eloACC;
    }
    public void setEloACC(final int eloACC) {
        this.eloACC = eloACC;
    }

    public int getEloF1() {
        return eloF1;
    }
    public void setEloF1(final int eloF1) {
        this.eloF1 = eloF1;
    }

    public Driver getDriver() {
        return driver;
    }
    public void setDriver(final Driver driver) {
        this.driver = driver;
    }
}
