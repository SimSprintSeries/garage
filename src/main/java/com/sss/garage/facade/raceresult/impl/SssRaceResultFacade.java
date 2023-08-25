package com.sss.garage.facade.raceresult.impl;

import com.sss.garage.data.raceresult.RaceResultData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.raceresult.RaceResultFacade;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.race.Race;
import com.sss.garage.model.raceresult.RaceResult;
import com.sss.garage.service.driver.DriverService;
import com.sss.garage.service.race.RaceService;
import com.sss.garage.service.raceresult.RaceResultService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SssRaceResultFacade extends SssBaseFacade implements RaceResultFacade {

    private RaceResultService raceResultService;

    private DriverService driverService;

    private RaceService raceService;

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

    @Override
    public Page<RaceResultData> getRaceResultsPaginated(final String finishPosition, final Boolean polePosition, final Boolean dnf, final Boolean dsq
            , final Boolean fastestLap, final String driverId, final String raceId, final Pageable pageable) {
        final Driver driver;
        if(Strings.isNotEmpty(driverId)) {
            driver = driverService.getDriver(Long.valueOf(driverId)).orElseThrow();
        }
        else {
            driver = null;
        }

        final Race race;
        if(Strings.isNotEmpty(raceId)) {
            race = raceService.findById(Long.valueOf(raceId)).orElseThrow();
        }
        else {
            race = null;
        }

        Page<RaceResult> raceResult = raceResultService.getRaceResultsPaginated(finishPosition, polePosition, dnf, dsq, fastestLap, driver, race, pageable);
        return raceResult.map(r -> conversionService.convert(r, RaceResultData.class));
    }

    @Autowired
    public void setRaceResultService(RaceResultService raceResultService) {
        this.raceResultService = raceResultService;
    }

    @Autowired
    public void setRaceService(final RaceService raceService) {
        this.raceService = raceService;
    }

    @Autowired
    public void setDriverService(final DriverService driverService) {
        this.driverService = driverService;
    }
}
