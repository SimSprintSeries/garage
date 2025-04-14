package com.sss.garage.model.presence;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.event.Event;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@IdClass(PresenceId.class)
public class Presence {
    @Id
    @ManyToOne
    private Event event;

    @Id
    @ManyToOne
    private Driver driver;

    private Boolean isPresent;

    private Date date;

    public Event getEvent() {
        return event;
    }

    public void setEvent(final Event event) {
        this.event = event;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(final Driver driver) {
        this.driver = driver;
    }

    public Boolean getIsPresent() {
        return isPresent;
    }

    public void setIsPresent(final Boolean isPresent) {
        this.isPresent = isPresent;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }
}
