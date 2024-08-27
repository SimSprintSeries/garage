package com.sss.garage.model.driver;

import com.sss.garage.model.event.Event;
import com.sss.garage.model.league.League;
import com.sss.garage.model.race.Race;
import com.sss.garage.model.split.Split;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    @Query("SELECT rr.driver FROM RaceResult rr LEFT JOIN Race r ON rr.race = r LEFT JOIN Event e ON r.event = e " +
            "WHERE e.league=:league")
    List<Driver> findDriversByLeague(League league);

    @Query("SELECT rr.driver FROM RaceResult rr WHERE rr.race=:race")
    List<Driver> findDriversByRace(Race race);

    List<Driver> findDriversByLeaguesIsContaining(League league);

    @Query("SELECT d FROM Driver d LEFT JOIN DiscordUser u ON d.discordUser = u " +
            "WHERE u.steamId = :steamId OR :steamId = NULL")
    Driver findBySteamId(final Long steamId);
}
