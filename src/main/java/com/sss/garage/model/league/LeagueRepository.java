package com.sss.garage.model.league;

import com.sss.garage.model.driver.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {
    @Query("SELECT l FROM League l WHERE (l.platform=:platform OR :platform IS NULL) " +
            "AND (l.name=:name OR :name IS NULL)" +
            "AND (l.active=:active OR :active IS NULL)")
    Page<League> findAllByParams(String platform, String name, Boolean active, Pageable pageable);

    @Query("SELECT DISTINCT e.league FROM Event e WHERE e.id IN(SELECT e.id FROM RaceResult rr LEFT JOIN Race r ON rr.race = r LEFT JOIN Race pr ON r.parentRaceEvent = pr " +
            "LEFT JOIN Event e ON pr.event = e " +
            "WHERE rr.driver=:driver) " +
            "OR e.id IN(SELECT e.id FROM RaceResult rr LEFT JOIN Race r ON rr.race = r LEFT JOIN Event e ON r.event = e " +
            "WHERE rr.driver=:driver)")
    Page<League> findLeaguesForDriver(Driver driver, Pageable pageable);
}
