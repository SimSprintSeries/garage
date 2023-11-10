package com.sss.garage.facade.stats;

import com.sss.garage.data.stats.StatsData;

public interface StatsFacade {
    StatsData getStats(final String driverId, final String gameId);
}
