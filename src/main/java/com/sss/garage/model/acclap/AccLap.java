package com.sss.garage.model.acclap;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class AccLap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer carId;

    private Integer driverIndex;

    private Boolean isValidForBest;

    private String laptime;

    private String sector1;

    private String sector2;

    private String sector3;

    private String firstName;

    private String lastName;

    private String shortName;

    private Integer carModel;

    private String carName;

    private Integer raceNumber;

    private String trackName;

    private String sessionType;

    private String serverName;

    private Date startDate;

    private Integer lapCount;

    private String theoreticalBest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getDriverIndex() {
        return driverIndex;
    }

    public void setDriverIndex(Integer driverIndex) {
        this.driverIndex = driverIndex;
    }

    public Boolean getIsValidForBest() {
        return isValidForBest;
    }

    public void setIsValidForBest(Boolean isValidForBest) {
        this.isValidForBest = isValidForBest;
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

    public Integer getCarModel() {
        return carModel;
    }

    public void setCarModel(Integer carModel) {
        this.carModel = carModel;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Integer getRaceNumber() {
        return raceNumber;
    }

    public void setRaceNumber(Integer raceNumber) {
        this.raceNumber = raceNumber;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getSessionType() {
        return sessionType;
    }

    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getLapCount() {
        return lapCount;
    }

    public void setLapCount(Integer lapCount) {
        this.lapCount = lapCount;
    }

    public String getTheoreticalBest() {
        return theoreticalBest;
    }

    public void setTheoreticalBest(String theoreticalBest) {
        this.theoreticalBest = theoreticalBest;
    }
}
