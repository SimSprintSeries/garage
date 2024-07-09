package com.sss.garage.service.presence.impl;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.event.Event;
import com.sss.garage.model.presence.Presence;
import com.sss.garage.model.presence.PresenceRepository;
import com.sss.garage.model.race.Race;
import com.sss.garage.service.presence.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SssPresenceService implements PresenceService {
    private PresenceRepository presenceRepository;

    @Override
    public void setPresenceForDriverAndRace(final Presence presence) {
        presenceRepository.save(presence);
    }

    @Override
    public Presence getByDriverAndEvent(Driver driver, Event event) {
        return presenceRepository.findByDriverAndEvent(driver, event);
    }

    @Autowired
    public void setPresenceRepository(final PresenceRepository presenceRepository) {
        this.presenceRepository = presenceRepository;
    }
}
