package com.sss.garage.model.acclap;

import jakarta.persistence.*;

@Entity
public class AccLap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer carId;

    private Integer driverIndex;

    private Boolean isValidForBest;

    private Float laptime;

    private Float sector1;

    private Float sector2;

    private Float sector3;

    private String firstName;

    private String lastName;

    private String shortName;

    private Integer carModel;

    private Integer raceNumber;

    private String trackName;

    private String sessionType;

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

    public Float getLaptime() {
        return laptime;
    }

    public void setLaptime(Float laptime) {
        this.laptime = laptime;
    }

    public Float getSector1() {
        return sector1;
    }

    public void setSector1(Float sector1) {
        this.sector1 = sector1;
    }

    public Float getSector2() {
        return sector2;
    }

    public void setSector2(Float sector2) {
        this.sector2 = sector2;
    }

    public Float getSector3() {
        return sector3;
    }

    public void setSector3(Float sector3) {
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
}
