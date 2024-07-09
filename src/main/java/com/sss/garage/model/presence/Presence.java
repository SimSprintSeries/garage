package com.sss.garage.model.presence;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.event.Event;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Presence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Event event;

    @ManyToOne
    private Driver driver;

    private Boolean isPresent;

    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

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
