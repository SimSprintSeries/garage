package com.sss.garage.facade.race.impl;

import java.util.Objects;

import com.sss.garage.data.race.RaceData;
import com.sss.garage.facade.race.RaceFacade;
import com.sss.garage.model.race.Race;
import com.sss.garage.service.race.RaceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SssRaceFacade implements RaceFacade {

    private ConversionService conversionService;
    private RaceService raceService;

    @Override
    public Page<RaceData> getRacesPaginated(final Boolean completed, final Pageable pageable) {
        Page<Race> races;
        if(Objects.isNull(completed)) {
            races = raceService.getAllParentRaces(pageable);
        }
        else if(completed) {
            races = raceService.getCompletedParentRaces(pageable);
        }
        else {
            races = raceService.getUncompletedParentRaces(pageable);
        }

        return races.map(r -> conversionService.convert(r, RaceData.class));
    }

    @Autowired
    public void setConversionService(final ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Autowired
    public void setRaceService(final RaceService raceService) {
        this.raceService = raceService;
    }
}
