package com.sss.garage.facade.presence.impl;

import com.sss.garage.data.driver.DriverData;
import com.sss.garage.data.presence.PresenceData;
import com.sss.garage.data.race.RaceData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.presence.PresenceFacade;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.presence.Presence;
import com.sss.garage.model.race.Race;
import com.sss.garage.service.driver.DriverService;
import com.sss.garage.service.presence.PresenceService;
import com.sss.garage.service.race.RaceService;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SssPresenceFacade extends SssBaseFacade implements PresenceFacade {
    private PresenceService presenceService;

    private RaceService raceService;

    private DriverService driverService;

    @Override
    public void setPresenceForDriverAndRace(final Boolean isPresent, @NotEmpty final String raceId, @NotEmpty final String driverId) {
        final PresenceData presenceData = new PresenceData();
        presenceData.setIsPresent(isPresent);
        presenceData.setRace(conversionService.convert(raceService.findById(Long.valueOf(raceId)).orElseThrow(), RaceData.class));
        presenceData.setDriver(conversionService.convert(driverService.getDriver(Long.valueOf(driverId)).orElseThrow(), DriverData.class));
        presenceService.setPresenceForDriverAndRace(conversionService.convert(presenceData, Presence.class));
    }

    @Override
    public PresenceData getByDriverAndRace(DriverData driver, RaceData race) {
        Presence presence = presenceService.getByDriverAndRace(conversionService.convert(driver, Driver.class),
                conversionService.convert(race, Race.class));
        return conversionService.convert(presence, PresenceData.class);
    }

    @Autowired
    public void setPresenceService(final PresenceService presenceService) {
        this.presenceService = presenceService;
    }

    @Autowired
    public void setRaceService(final RaceService raceService) {
        this.raceService = raceService;
    }

    @Autowired
    public void setDriverService(final DriverService driverService) {
        this.driverService = driverService;
    }
}
