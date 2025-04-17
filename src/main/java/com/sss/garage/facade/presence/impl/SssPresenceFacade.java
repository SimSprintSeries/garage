package com.sss.garage.facade.presence.impl;

import com.sss.garage.data.driver.DriverData;
import com.sss.garage.data.event.EventData;
import com.sss.garage.data.presence.PresenceData;
import com.sss.garage.data.race.RaceData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.presence.PresenceFacade;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.event.Event;
import com.sss.garage.model.presence.Presence;
import com.sss.garage.model.race.Race;
import com.sss.garage.service.driver.DriverService;
import com.sss.garage.service.event.EventService;
import com.sss.garage.service.presence.PresenceService;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SssPresenceFacade extends SssBaseFacade implements PresenceFacade {
    private PresenceService presenceService;

    private EventService eventService;

    private DriverService driverService;

    @Override
    public void setPresenceForDriverAndEvent(final Boolean isPresent, @NotEmpty final String eventId, @NotEmpty final String driverId) {
        final PresenceData presenceData = new PresenceData();
        presenceData.setIsPresent(isPresent);
        presenceData.setEvent(conversionService.convert(eventService.getEvent(Long.valueOf(eventId)).orElseThrow(), EventData.class));
        presenceData.setDriver(conversionService.convert(driverService.getDriver(Long.valueOf(driverId)).orElseThrow(), DriverData.class));
        presenceService.setPresenceForDriverAndRace(conversionService.convert(presenceData, Presence.class));
    }

    @Override
    public PresenceData getPresence(@NotEmpty final String eventId, @NotEmpty final String driverId) {
        Presence presence = presenceService.getByDriverAndEvent(driverService.getDriver(Long.valueOf(driverId)).orElseThrow(),
                eventService.getEvent(Long.valueOf(eventId)).orElseThrow());
        return conversionService.convert(presence, PresenceData.class);
    }

    @Override
    public void deleteByDriverAndRace(@NotEmpty final String driverId, @NotEmpty final String eventId) {
        presenceService.deleteByDriverAndEvent(driverService.getDriver(Long.valueOf(driverId)).orElseThrow(),
                eventService.getEvent(Long.valueOf(eventId)).orElseThrow());
    }

    @Autowired
    public void setPresenceService(final PresenceService presenceService) {
        this.presenceService = presenceService;
    }

    @Autowired
    public void setEventService(final EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setDriverService(final DriverService driverService) {
        this.driverService = driverService;
    }
}
