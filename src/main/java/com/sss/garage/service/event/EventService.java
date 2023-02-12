package com.sss.garage.service.event;

import com.sss.garage.model.event.Event;
import com.sss.garage.model.race.Race;

public interface EventService {
    Event getEvent(final Race race);
}
