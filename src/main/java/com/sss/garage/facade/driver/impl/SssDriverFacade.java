package com.sss.garage.facade.driver.impl;

import com.sss.garage.data.driver.DriverData;
import com.sss.garage.data.team.TeamData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.driver.DriverFacade;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.event.Event;
import com.sss.garage.model.league.League;
import com.sss.garage.model.race.Race;
import com.sss.garage.model.split.Split;
import com.sss.garage.service.driver.DriverService;
import com.sss.garage.service.event.EventService;
import com.sss.garage.service.league.LeagueService;
import com.sss.garage.service.race.RaceService;
import com.sss.garage.service.split.SplitService;
import com.sss.garage.service.team.TeamService;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.Set;

@Service
public class SssDriverFacade extends SssBaseFacade implements DriverFacade {
    private DriverService driverService;

    private LeagueService leagueService;

    private TeamService teamService;

    private EventService eventService;

    private SplitService splitService;

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

    @Override
    public Page<DriverData> getDriversByEvent(@NotEmpty final String eventId, final Pageable pageable) {
        final Event event = eventService.getEvent(Long.valueOf(eventId)).orElseThrow();
        return driverService.getDriversByEvent(event, pageable)
                .map(d -> conversionService.convert(d, DriverData.class));
    }

    @Override
    public Page<DriverData> getDriversBySplit(@NotEmpty final String splitId, final Pageable pageable) {
        final Split split = splitService.getSplit(Long.valueOf(splitId)).orElseThrow();
        return driverService.getDriversBySplit(split, pageable)
                .map(d -> conversionService.convert(d, DriverData.class));
    }

    @Override
    public void setDriversForSplit(@NotEmpty final String splitId, final List<DriverData> driversData) {
        final Split split = splitService.getSplit(Long.valueOf(splitId)).orElseThrow();
        List<Driver> drivers = driversData.stream().map(d -> conversionService.convert(d, Driver.class)).toList();
        drivers.forEach(d -> d.setSplits(Set.of(split)));
        driverService.setDriversForSplit(drivers);
    }

    @Autowired
    public void setDriverService(final DriverService driverService) {
        this.driverService = driverService;
    }

    @Autowired
    public void setLeagueService(final LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @Autowired
    public void setTeamService(final TeamService teamService) {
        this.teamService = teamService;
    }

    @Autowired
    public void setEventService(final EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setSplitService(final SplitService splitService) {
        this.splitService = splitService;
    }
}
