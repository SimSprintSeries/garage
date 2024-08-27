package com.sss.garage.dto.acclap;

import com.sss.garage.dto.driver.SimpleDriverDTO;

public class AccLapDTO {
    private Long id;

    private String laptime;

    private String sector1;

    private String sector2;

    private String sector3;

    private String firstName;

    private String lastName;

    private String shortName;

    private String steamId;

    private String carName;

    private Integer validLaps;

    private String theoreticalBest;

    private String totalTime;

    private Integer totalLaps;

    private SimpleDriverDTO driver;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLaptime() {
        return laptime;
    }

    public void setLaptime(String laptime) {
        this.laptime = laptime;
    }

    public String getSector1() {
        return sector1;
    }

    public void setSector1(String sector1) {
        this.sector1 = sector1;
    }

    public String getSector2() {
        return sector2;
    }

    public void setSector2(String sector2) {
        this.sector2 = sector2;
    }

    public String getSector3() {
        return sector3;
    }

    public void setSector3(String sector3) {
        this.sector3 = sector3;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getSteamId() {
        return steamId;
    }

    public void setSteamId(String steamId) {
        this.steamId = steamId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Integer getValidLaps() {
        return validLaps;
    }

    public void setValidLaps(Integer validLaps) {
        this.validLaps = validLaps;
    }

    public String getTheoreticalBest() {
        return theoreticalBest;
    }

    public void setTheoreticalBest(String theoreticalBest) {
        this.theoreticalBest = theoreticalBest;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(final String totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getTotalLaps() {
        return totalLaps;
    }

    public void setTotalLaps(final Integer totalLaps) {
        this.totalLaps = totalLaps;
    }

    public SimpleDriverDTO getDriver() {
        return driver;
    }

    public void setDriver(final SimpleDriverDTO driver) {
        this.driver = driver;
    }
}
