package com.sss.garage.model.driver;

import com.sss.garage.model.league.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    @Query("SELECT rr.driver FROM RaceResult rr WHERE rr.id IN(SELECT rr.id FROM RaceResult rr LEFT JOIN Race r ON rr.race = r LEFT JOIN Race pr ON r.parentRaceEvent = pr " +
            "LEFT JOIN Event e ON pr.event = e " +
            "WHERE e.league=:league) " +
            "OR rr.id IN(SELECT rr.id FROM RaceResult rr LEFT JOIN Race r ON rr.race = r LEFT JOIN Event e ON r.event = e " +
            "WHERE e.league=:league)")
    List<Driver> findDriversByLeague(League league);
}
