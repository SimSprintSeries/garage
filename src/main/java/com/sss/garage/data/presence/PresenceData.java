package com.sss.garage.data.presence;

import com.sss.garage.data.driver.DriverData;
import com.sss.garage.data.race.RaceData;

public class PresenceData {
    private Long id;

    private RaceData race;

    private DriverData driver;

    private Boolean isPresent;

    private Boolean isAssignedToSplit;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public RaceData getRace() {
        return race;
    }

    public void setRace(final RaceData race) {
        this.race = race;
    }

    public DriverData getDriver() {
        return driver;
    }

    public void setDriver(final DriverData driver) {
        this.driver = driver;
    }

    public Boolean getIsPresent() {
        return isPresent;
    }

    public void setIsPresent(final Boolean isPresent) {
        this.isPresent = isPresent;
    }

    public Boolean getIsAssignedToSplit() {
        return isAssignedToSplit;
    }

    public void setIsAssignedToSplit(final Boolean isAssignedToSplit) {
        this.isAssignedToSplit = isAssignedToSplit;
    }
}
