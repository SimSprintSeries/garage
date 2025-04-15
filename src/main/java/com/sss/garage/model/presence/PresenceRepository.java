package com.sss.garage.model.presence;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.event.Event;
import com.sss.garage.model.race.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PresenceRepository extends JpaRepository<Presence, Long> {
    Presence findByDriverAndEvent(final Driver driver, final Event event);

    void deleteByDriverAndEvent(final Driver driver, final Event event);
}
