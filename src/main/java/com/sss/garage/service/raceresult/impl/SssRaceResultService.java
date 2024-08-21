package com.sss.garage.service.raceresult.impl;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.race.Race;
import com.sss.garage.model.racepointdictionary.RacePointDictionary;
import com.sss.garage.model.racepointdictionary.RacePointDictionaryRepository;
import com.sss.garage.model.raceresult.RaceResult;
import com.sss.garage.model.raceresult.RaceResultRepository;
import com.sss.garage.service.driver.DriverService;
import com.sss.garage.service.raceresult.RaceResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SssRaceResultService implements RaceResultService {

    private RaceResultRepository raceResultRepository;

    private RacePointDictionaryRepository racePointDictionaryRepository;

    private DriverService driverService;
    
    private CacheManager cacheManager;

    @Override
    public Integer calculateAndSaveTotalDriverWins(final Driver driver) {
        final Integer totalWins = raceResultRepository.countRaceResultsByDriverAndFinishPosition(driver, 1);
        driver.setTotalWins(totalWins);
        driverService.saveDriver(driver);

        return totalWins;
    }

    @Override
    public Integer calculateAndSaveTotalDriverTopTenResults(final Driver driver) {
        final Integer totalTopTenResults = raceResultRepository.countRaceResultsByDriverAndFinishPositionLessThanEqual(driver, 10);
        driver.setTotalTopTenResults(totalTopTenResults);
        driverService.saveDriver(driver);

        return totalTopTenResults;
    }

    @Override
    public Integer calculateAndSaveTotalRacesDriven(final Driver driver) {
        final Integer allRaceResults = raceResultRepository.countRaceResultByDriver(driver);// maybe needs some filtering?
        driver.setTotalRacesDriven(allRaceResults);
        driverService.saveDriver(driver);

        return allRaceResults;
    }

    @Override
    public Integer calculateAndSaveTotalPodiums(final Driver driver) {
        final Integer totalPodiums = raceResultRepository.countRaceResultsByDriverAndFinishPositionLessThanEqual(driver, 3);
        driver.setPodiums(totalPodiums);
        driverService.saveDriver(driver);

        return totalPodiums;
    }

    @Override
    public Integer calculateAndSaveFastestLaps(final Driver driver) {
        final Integer totalFastestLaps = raceResultRepository.countRaceResultByDriverAndFastestLap(driver, true);
        driver.setFastestLaps(totalFastestLaps);
        driverService.saveDriver(driver);

        return totalFastestLaps;
    }

    @Override
    public Integer calculateAndSavePolePositions(final Driver driver) {
        final Integer polePositions = raceResultRepository.countRaceResultByDriverAndPolePosition(driver, true);
        driver.setPolePositions(polePositions);
        driverService.saveDriver(driver);

        return polePositions;
    }

    @Override
    public Optional<RaceResult> getRaceResult(final Long id) {
        return raceResultRepository.findById(id);
    }

    @Override
    public void createRaceResults(final List<RaceResult> raceResults) {
        setPointsForPosition(raceResults);
        final Cache cache = cacheManager.getCache("driverPositionsCount");
        raceResults.forEach(rr -> {
            cache.evict(rr.getDriver().getId() + "-" + rr.getRace().getLeague().getId() + "-" + rr.getFinishPosition());
        });
        raceResultRepository.saveAll(raceResults);
    }

    @Override
    public void deleteRaceResult(final Long id) {
        raceResultRepository.deleteById(id);
    }

    @Override
    public Page<RaceResult> getRaceResultsPaginated(final String finishPosition, final Boolean polePosition, final Boolean dnf, final Boolean dsq
            , final Boolean fastestLap, final Driver driver, final Race race, final Pageable pageable) {
        return raceResultRepository.findAllByParams(finishPosition, polePosition, dnf, dsq, fastestLap, driver, race, pageable);
    }

    private Integer findPointsForPosition(final RaceResult raceResult) {
        return racePointDictionaryRepository.findByRacePointType(raceResult.getRace().getPointType())
                .orElseThrow().pointsForPosition(raceResult.getFinishPosition());
    }

    private void setPointsForPosition(List<RaceResult> raceResults) {
        List<RaceResult> raceResultsNew = new ArrayList<>();
        for(RaceResult raceResult : raceResults) {
            RacePointDictionary racePointDictionary = racePointDictionaryRepository.findByRacePointType(raceResult.getRace().getPointType())
                    .orElseThrow();
            if(raceResult.getPointsForPosition() == null) {
                Integer points = findPointsForPosition(raceResult);
                if(racePointDictionary.getPolePositionScored() && raceResult.getPolePosition()) {
                    points = points + racePointDictionary.getPolePositionPoints();
                }
                if(racePointDictionary.getFastestLapScored()) {
                    if((!racePointDictionary.getFastestLapForTop10() || raceResult.getFinishPosition() < 11) && raceResult.getFastestLap()) {
                        points = points + racePointDictionary.getFastestLapPoints();
                    }
                }
                raceResult.setPointsForPosition(points);
                raceResultsNew.add(raceResult);
            }
        }
        raceResultRepository.saveAll(raceResultsNew);
    }

    @Autowired
    public void setRaceResultRepository(RaceResultRepository raceResultRepository) {
        this.raceResultRepository = raceResultRepository;
    }

    @Autowired
    public void setRacePointDictionaryRepository(RacePointDictionaryRepository racePointDictionaryRepository) {
        this.racePointDictionaryRepository = racePointDictionaryRepository;
    }

    @Autowired
    public void setDriverService(final DriverService driverService) {
        this.driverService = driverService;
    }

    @Autowired
    public void setCacheManager(final CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
    
}
