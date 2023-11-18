package com.sss.garage.service.stats;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.game.Game;
import com.sss.garage.model.league.League;
import com.sss.garage.model.stats.Stats;

public interface StatsService {
    Stats getStats(final Driver driver, final League league, final Game game, final String split);
}
