package com.sss.garage.facade.league.impl;

import com.sss.garage.data.league.DetailedLeagueData;
import com.sss.garage.data.league.LeagueData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.league.LeagueFacade;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.league.League;
import com.sss.garage.service.driver.DriverService;
import com.sss.garage.service.league.LeagueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SssLeagueFacade extends SssBaseFacade implements LeagueFacade  {

    private LeagueService leagueService;

    private DriverService driverService;

    @Override
    public LeagueData getLeague(final Long id) {
        return leagueService.getLeague(id)
                .map(l -> conversionService.convert(l, DetailedLeagueData.class))
                .orElseThrow();
    }

    @Override
    public Page<LeagueData> getLeaguesPaginated(final String platform, final String name, final Boolean active, final Pageable pageable) {
        Page<League> league = leagueService.getLeaguesPaginated(platform, name, active, pageable);
        return league.map(l -> conversionService.convert(l, LeagueData.class));
    }

    @Override
    public void createLeague(final LeagueData leagueData) throws IOException {
        leagueService.createLeague(conversionService.convert(leagueData, League.class));
    }

    @Override
    public void deleteLeague(final Long id) {
        leagueService.deleteLeague(id);
    }

    @Override
    public Page<LeagueData> getLeaguesForDriver(final String driverId, final Pageable pageable) {
        Driver driver = driverService.getDriver(Long.valueOf(driverId)).orElseThrow();
        Page<League> leagues = leagueService.getLeaguesForDriver(driver, pageable);
        return leagues.map(l -> conversionService.convert(l, LeagueData.class));
    }

    @Override
    public LeagueData getLeagueByName(final String name) {
        return conversionService.convert(leagueService.getLeagueByName(name), LeagueData.class);
    }

    @Autowired
    public void setLeagueService(final LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @Autowired
    public void setDriverService(DriverService driverService) {
        this.driverService = driverService;
    }
}
