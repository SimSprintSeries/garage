package com.sss.garage.facade.presence;

import com.sss.garage.data.driver.DriverData;
import com.sss.garage.data.event.EventData;
import com.sss.garage.data.presence.PresenceData;

public interface PresenceFacade {
    void setPresenceForDriverAndEvent(final Boolean isPresent, final String eventId, final String driverId);

    PresenceData getByDriverAndRace(DriverData driver, EventData event);
}
