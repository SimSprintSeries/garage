package com.sss.garage.dev.initial.data.legacy.model;

public class LegacyAccLap {
    public Long id;

    public Integer carId;

    public Integer driverIndex;

    public Boolean isValidForBest;

    public Integer laptime;

    public Integer sector1;

    public Integer sector2;

    public Integer sector3;

    public String firstName;

    public String lastName;

    public String shortName;

    public Integer raceNumber;

    public Integer carModel;

    public String trackName;

    public String sessionType;

    public String serverName;

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

    public Integer getLaptime() {
            return laptime;
        }

    public void setLaptime(Integer laptime) {
            this.laptime = laptime;
        }

    public Integer getSector1() {
            return sector1;
        }

    public void setSector1(Integer sector1) {
            this.sector1 = sector1;
        }

    public Integer getSector2() {
        return sector2;
    }

    public void setSector2(Integer sector2) {
        this.sector2 = sector2;
    }

    public Integer getSector3() {
        return sector3;
    }

    public void setSector3(Integer sector3) {
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

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
