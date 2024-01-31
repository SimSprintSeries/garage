package com.sss.garage.facade.race.impl;

import java.util.List;
import java.util.Objects;

import com.sss.garage.data.race.RaceData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.race.RaceFacade;
import com.sss.garage.model.event.Event;
import com.sss.garage.model.event.EventRepository;
import com.sss.garage.model.league.League;
import com.sss.garage.model.race.Race;
import com.sss.garage.service.league.LeagueService;
import com.sss.garage.service.race.RaceService;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SssRaceFacade extends SssBaseFacade implements RaceFacade {

    private RaceService raceService;

    private LeagueService leagueService;

    private EventRepository eventRepository;

    @Override
    public Page<RaceData> getRacesPaginated(final String leagueId, final Boolean completed, final Pageable pageable) {
        Page<Race> races;
        League league = null;
        if(Strings.isNotEmpty(leagueId)) {
            league = leagueService.getLeague(Long.valueOf(leagueId)).orElseThrow();
        }
        if(Objects.isNull(completed)) {
            races = raceService.getAllPlayableRaces(league, pageable);
        }
        else if(completed) {
            races = raceService.getCompletedPlayableRaces(league, pageable);
        }
        else {
            races = raceService.getUncompletedPlayableRaces(league, pageable);
        }

        return races.map(r -> conversionService.convert(r, RaceData.class));
    }

    @Override
    public RaceData getRace(final Long id) {
         return conversionService.convert(raceService.findById(id).orElseThrow(), RaceData.class);
    }

    @Override
    public void createRace(final RaceData raceData) {
        raceService.save(conversionService.convert(raceData, Race.class));
    }

    @Override
    public void deleteRace(final Long id) {
        raceService.deleteRace(id);
    }

    @Override
    public Page<RaceData> getAllRacesByEvent(String eventId, Pageable pageable) {
        Event event = null;
        if(Strings.isNotEmpty(eventId)) {
            event = eventRepository.findById(Long.valueOf(eventId)).orElseThrow();
        }
        Page<Race> races = raceService.getAllRacesByEvent(event, pageable);
        return races.map(r -> conversionService.convert(r, RaceData.class));
    }

    @Autowired
    public void setRaceService(final RaceService raceService) {
        this.raceService = raceService;
    }

    @Autowired
    public void setLeagueService(final LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @Autowired
    public void setEventRepository(final EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
}
