package com.sss.garage.facade.stats.impl;

import com.sss.garage.data.driver.DriverData;
import com.sss.garage.data.game.GameData;
import com.sss.garage.data.race.RaceData;
import com.sss.garage.data.stats.StatsData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.stats.StatsFacade;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.game.Game;
import com.sss.garage.model.league.League;
import com.sss.garage.model.race.Race;
import com.sss.garage.service.driver.DriverService;
import com.sss.garage.service.game.GameService;
import com.sss.garage.service.league.LeagueService;
import com.sss.garage.service.race.RaceService;
import com.sss.garage.service.stats.StatsService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SssStatsFacade extends SssBaseFacade implements StatsFacade {
    private StatsService statsService;

    private DriverService driverService;

    private LeagueService leagueService;

    @Override
    public StatsData getStats(final String driverId, final String leagueId) {
        Driver driver = null;
        League league = null;
        if(Strings.isNotEmpty(driverId)) {
            driver = driverService.getDriver(Long.valueOf(driverId)).orElseThrow();
        }
        if(Strings.isNotEmpty(leagueId)) {
            league = leagueService.getLeague(Long.valueOf(leagueId)).orElseThrow();
        }
        return conversionService.convert(statsService.getStats(driver, league), StatsData.class);
    }

    @Autowired
    public void setStatsService(StatsService statsService) {
        this.statsService = statsService;
    }

    @Autowired
    public void setDriverService(DriverService driverService) {
        this.driverService = driverService;
    }

    @Autowired
    public void setLeagueService(LeagueService leagueService) {
        this.leagueService = leagueService;
    }
}
