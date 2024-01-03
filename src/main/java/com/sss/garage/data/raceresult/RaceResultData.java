package com.sss.garage.data.raceresult;

import com.sss.garage.data.driver.DriverData;
import com.sss.garage.data.race.RaceData;

public class RaceResultData {
    private Long id;

    private Integer finishPosition;

    private Boolean polePosition;

    private Boolean dnf;

    private Boolean dsq;

    private Boolean fastestLap;

    private DriverData driverData;

    private RaceData raceData;

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

    public Boolean getDsq() {
        return dsq;
    }

    public void setDsq(final Boolean dsq) {
        this.dsq = dsq;
    }

    public Boolean getFastestLap() {
        return fastestLap;
    }

    public void setFastestLap(final Boolean fastestLap) {
        this.fastestLap = fastestLap;
    }

    public DriverData getDriver() {
        return driverData;
    }

    public void setDriver(DriverData driverData) {
        this.driverData = driverData;
    }

    public RaceData getRace() {
        return raceData;
    }

    public void setRace(RaceData raceData) {
        this.raceData = raceData;
    }

    public Integer getPointsForPosition() {
        return pointsForPosition;
    }

    public void setPointsForPosition(Integer pointsForPosition) {
        this.pointsForPosition = pointsForPosition;
    }
}
