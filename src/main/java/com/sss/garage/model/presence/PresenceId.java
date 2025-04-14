package com.sss.garage.model.presence;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.event.Event;

import java.io.Serializable;

public class PresenceId implements Serializable {
    private Event event;

    private Driver driver;

    public PresenceId(Event event, Driver driver) {
        this.event = event;
        this.driver = driver;
    }

    public PresenceId(){}
}
