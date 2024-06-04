package com.sss.garage.service.presence;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.presence.Presence;
import com.sss.garage.model.race.Race;

public interface PresenceService {
    void setPresenceForDriverAndRace(final Presence presence);

    Presence getByDriverAndRace(final Driver driver, final Race race);
}
