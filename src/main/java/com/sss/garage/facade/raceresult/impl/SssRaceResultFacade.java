package com.sss.garage.facade.raceresult.impl;

import com.sss.garage.data.raceresult.RaceResultData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.raceresult.RaceResultFacade;
import com.sss.garage.model.raceresult.RaceResult;
import com.sss.garage.service.raceresult.RaceResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SssRaceResultFacade extends SssBaseFacade implements RaceResultFacade {

    private RaceResultService raceResultService;

    @Override
    public RaceResultData getRaceResult(final Long id) {
        return raceResultService.getRaceResult(id).map(r -> conversionService.convert(r, RaceResultData.class)).orElseThrow();
    }

    @Override
    public List<RaceResultData> getAllRaceResults() {
        return raceResultService.getAllRaceResults().stream().map(r -> conversionService.convert(r, RaceResultData.class)).toList();
    }

    @Override
    public void createRaceResult(final RaceResultData raceResultData) {
        raceResultService.createRaceResult(conversionService.convert(raceResultData, RaceResult.class));
    }

    @Override
    public void deleteRaceResult(final Long id) {
        raceResultService.deleteRaceResult(id);
    }

    @Autowired
    public void setRaceResultService(RaceResultService raceResultService) {
        this.raceResultService = raceResultService;
    }
}
