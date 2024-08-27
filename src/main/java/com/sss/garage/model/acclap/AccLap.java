package com.sss.garage.model.acclap;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.track.Track;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class AccLap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isValidForBest;

    private String laptime;

    private String sector1;

    private String sector2;

    private String sector3;

    private String firstName;

    private String lastName;

    private String shortName;

    @ManyToOne
    private Driver driver;

    private String steamId;

    private Integer carModel;

    private String carName;

    private Integer raceNumber;

    @ManyToOne
    private Track track;

    private String sessionType;

    private String serverName;

    private Date startDate;

    private Integer validLaps;

    private String theoreticalBest;

    private String totalTime;

    private Integer totalLaps;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Boolean getIsValidForBest() {
        return isValidForBest;
    }

    public void setIsValidForBest(final Boolean isValidForBest) {
        this.isValidForBest = isValidForBest;
    }

    public String getLaptime() {
        return laptime;
    }

    public void setLaptime(final String laptime) {
        this.laptime = laptime;
    }

    public String getSector1() {
        return sector1;
    }

    public void setSector1(final String sector1) {
        this.sector1 = sector1;
    }

    public String getSector2() {
        return sector2;
    }

    public void setSector2(final String sector2) {
        this.sector2 = sector2;
    }

    public String getSector3() {
        return sector3;
    }

    public void setSector3(final String sector3) {
        this.sector3 = sector3;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(final String shortName) {
        this.shortName = shortName;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(final Driver driver) {
        this.driver = driver;
    }

    public String getSteamId() {
        return steamId;
    }

    public void setSteamId(final String steamId) {
        this.steamId = steamId;
    }

    public Integer getCarModel() {
        return carModel;
    }

    public void setCarModel(final Integer carModel) {
        this.carModel = carModel;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(final String carName) {
        this.carName = carName;
    }

    public Integer getRaceNumber() {
        return raceNumber;
    }

    public void setRaceNumber(final Integer raceNumber) {
        this.raceNumber = raceNumber;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(final Track track) {
        this.track = track;
    }

    public String getSessionType() {
        return sessionType;
    }

    public void setSessionType(final String sessionType) {
        this.sessionType = sessionType;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(final String serverName) {
        this.serverName = serverName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    public Integer getValidLaps() {
        return validLaps;
    }

    public void setValidLaps(final Integer validLaps) {
        this.validLaps = validLaps;
    }

    public String getTheoreticalBest() {
        return theoreticalBest;
    }

    public void setTheoreticalBest(final String theoreticalBest) {
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
}
