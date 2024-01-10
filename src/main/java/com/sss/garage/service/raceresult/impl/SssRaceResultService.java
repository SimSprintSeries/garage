package com.sss.garage.service.raceresult.impl;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.league.League;
import com.sss.garage.model.race.Race;
import com.sss.garage.model.racepointdictionary.RacePointDictionary;
import com.sss.garage.model.racepointdictionary.RacePointDictionaryRepository;
import com.sss.garage.model.racepointtype.RacePointType;
import com.sss.garage.model.raceresult.RaceResult;
import com.sss.garage.model.raceresult.RaceResultRepository;
import com.sss.garage.service.raceresult.RaceResultService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public List<RaceResult> getAllRaceResults() {
        return raceResultRepository.findAll();
    }

    @Override
    public Optional<RaceResult> getRaceResult(final Long id) {
        setPointsForPosition();
        return raceResultRepository.findById(id);
    }

    @Override
    public void createRaceResult(final RaceResult raceResult) {
        raceResultRepository.save(raceResult);
    }

    @Override
    public void deleteRaceResult(final Long id) {
        raceResultRepository.deleteById(id);
    }

    @Override
    public Page<RaceResult> getRaceResultsPaginated(final String finishPosition, final Boolean polePosition, final Boolean dnf, final Boolean dsq
            , final Boolean fastestLap, final Driver driver, final Race race, final Pageable pageable) {
        setPointsForPosition();
        return raceResultRepository.findAllByParams(finishPosition, polePosition, dnf, dsq, fastestLap, driver, race, pageable);
    }

    private Integer findPointsForPosition(final RaceResult raceResult) {
        return racePointDictionaryRepository.findByRacePointType(raceResult.getRace().getPointType())
                .orElseThrow().pointsForPosition(raceResult.getFinishPosition());
    }

    private void setPointsForPosition() {
        List<RaceResult> raceResults = new ArrayList<>();
        for(RaceResult raceResult : raceResultRepository.findAll()) {
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
                League league = null;
                try {
                    league = raceResult.getRace().getEvent().getLeague();
                } catch (NullPointerException e) {
                    league = raceResult.getRace().getParentRaceEvent().getEvent().getLeague();
                }
                System.out.println("pp scored: " + racePointDictionary.getPolePositionScored() +
                        ", fl scored: " + racePointDictionary.getFastestLapScored() +
                        ", has pp: " + raceResult.getPolePosition() +
                        ", has fl: " + raceResult.getFastestLap() +
                        ", position: " + raceResult.getFinishPosition() +
                        ", points: " + points +
                        ", league: " + league.getName());
                raceResult.setPointsForPosition(points);
                raceResults.add(raceResult);
            }
        }
        raceResultRepository.saveAll(raceResults);
    }

    @Autowired
    public void setRaceResultRepository(RaceResultRepository raceResultRepository) {
        this.raceResultRepository = raceResultRepository;
    }

    @Autowired
    public void setRacePointDictionaryRepository(RacePointDictionaryRepository racePointDictionaryRepository) {
        this.racePointDictionaryRepository = racePointDictionaryRepository;
    }
}
