package com.sss.garage.dto.presence;

import com.sss.garage.dto.driver.SimpleDriverDTO;

public class PresenceDTO {
    private Long id;

    private SimpleDriverDTO driver;

    private Boolean isPresent;

    private Boolean isAssignedToSplit;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public SimpleDriverDTO getDriver() {
        return driver;
    }

    public void setDriver(final SimpleDriverDTO driver) {
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
