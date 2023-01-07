package com.sss.garage.model.role;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Event {

    @Id
    private long id;
    private LocalDateTime startTime;

    public long getId() {
        return id;
    }
    public void setId(final long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(final LocalDateTime startTime) {
        this.startTime = startTime;
    }

}
