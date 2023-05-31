package com.sss.garage.facade.race.impl;

import java.util.List;
import java.util.Objects;

import com.sss.garage.data.race.RaceData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.race.RaceFacade;
import com.sss.garage.model.race.Race;
import com.sss.garage.service.race.RaceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SssRaceFacade extends SssBaseFacade implements RaceFacade {

    private RaceService raceService;

    @Override
    public Page<RaceData> getRacesPaginated(final Boolean completed, final Pageable pageable) {
        Page<Race> races;
        if(Objects.isNull(completed)) {
            races = raceService.getAllPlayableRaces(pageable);
        }
        else if(completed) {
            races = raceService.getCompletedPlayableRaces(pageable);
        }
        else {
            races = raceService.getUncompletedPlayableRaces(pageable);
        }

        return races.map(r -> conversionService.convert(r, RaceData.class));
    }

    @Override
    public List<RaceData> getAllRaces() {
        return raceService.getAllRaces().stream().map(r -> conversionService.convert(r, RaceData.class)).toList();
    }

    @Override
    public RaceData getRace(final Long id) {
         return conversionService.convert(raceService.findById(id).orElseThrow(), RaceData.class) ;
    }

    @Override
    public void createRace(final RaceData raceData) {
        raceService.save(conversionService.convert(raceData, Race.class));
    }

    @Override
    public void deleteRace(final Long id) {
        raceService.deleteRace(id);
    }

    @Autowired
    public void setRaceService(final RaceService raceService) {
        this.raceService = raceService;
    }
}
