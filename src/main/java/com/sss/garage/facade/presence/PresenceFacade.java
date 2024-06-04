package com.sss.garage.facade.presence;

import com.sss.garage.data.driver.DriverData;
import com.sss.garage.data.presence.PresenceData;
import com.sss.garage.data.race.RaceData;

public interface PresenceFacade {
    void setPresenceForDriverAndRace(final Boolean isPresent, final String raceId, final String driverId);

    PresenceData getByDriverAndRace(DriverData driver, RaceData race);
}
