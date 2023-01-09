package com.sss.garage.model.race;

import java.util.Set;

import com.sss.garage.model.event.Event;
import com.sss.garage.model.raceresult.RaceResult;
import com.sss.garage.model.split.Split;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Race extends Event {

    @ManyToOne
    private Split split;

    @ManyToOne
    private Event event;

    @OneToMany(mappedBy = "race")
    private Set<RaceResult> raceResults;

    public Split getSplit() {
        return split;
    }

    public void setSplit(final Split split) {
        this.split = split;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(final Event event) {
        this.event = event;
    }

    public Set<RaceResult> getRaceResults() {
        return raceResults;
    }

    public void setRaceResults(final Set<RaceResult> raceResults) {
        this.raceResults = raceResults;
    }
}
