package com.sss.garage.facade.driver.impl;

import com.sss.garage.data.driver.DriverData;
import com.sss.garage.data.team.TeamData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.driver.DriverFacade;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.league.League;
import com.sss.garage.service.driver.DriverService;
import com.sss.garage.service.league.LeagueService;
import com.sss.garage.service.team.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.validation.constraints.NotEmpty;

@Service
public class SssDriverFacade extends SssBaseFacade implements DriverFacade {
    private DriverService driverService;

    private LeagueService leagueService;

    private TeamService teamService;

    @Override
    public DriverData getDriver(final Long id) {
        return conversionService.convert(driverService.getDriver(id).orElseThrow(), DriverData.class);
    }

    @Override
    public void createDriver(final DriverData driverData) {
        driverService.createDriver(conversionService.convert(driverData, Driver.class));
    }

    @Override
    public void deleteDriver(final Long id) {
        driverService.deleteDriver(id);
    }

    @Override
    public Page<DriverData> getDriversPaginated(final Pageable pageable) {
        Page<Driver> driver = driverService.getDriversPaginated(pageable);
        return driver.map(d -> conversionService.convert(d, DriverData.class));
    }

    @Override
    public Page<DriverData> getDriversByLeague(@NotEmpty final String leagueId, final Pageable pageable) {
        final League league = leagueService.getLeague(Long.valueOf(leagueId)).orElseThrow();
        return driverService.getDriversByLeague(league, pageable)
                .map(d -> {
                    // populate additional session-specific parameters
                    final DriverData data = conversionService.convert(d, DriverData.class);

                    teamService.findTeamForDriverAndLeague(d, league)
                            .ifPresent(t -> data.setTeam(conversionService.convert(t, TeamData.class)));

                    return data;
                });
    }

    @Autowired
    public void setDriverService(DriverService driverService) {
        this.driverService = driverService;
    }

    @Autowired
    public void setLeagueService(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @Autowired
    public void setTeamService(final TeamService teamService) {
        this.teamService = teamService;
    }
}
