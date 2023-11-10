package com.sss.garage.service.stats;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.game.Game;
import com.sss.garage.model.league.League;
import com.sss.garage.model.race.Race;
import com.sss.garage.model.stats.Stats;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StatsService {
    //Page<Stats> getStatsPaginated(final Driver driver, final Game game, final Race race, final Pageable pageable);

    Stats getStats(final Driver driver, final League league);
}
