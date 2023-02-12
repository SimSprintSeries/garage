package com.sss.garage.service.event.impl;

import com.sss.garage.model.event.Event;
import com.sss.garage.model.race.Race;
import com.sss.garage.service.event.EventService;

import org.springframework.stereotype.Service;

@Service
public class SssEventService implements EventService {

    @Override
    public Event getEvent(final Race race) {
        return race.getEvent() != null ? race.getEvent() : race.getParentRaceEvent().getEvent();
    }
}
