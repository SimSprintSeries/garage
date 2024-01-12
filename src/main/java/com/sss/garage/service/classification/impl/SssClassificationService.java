package com.sss.garage.service.classification.impl;

import com.sss.garage.model.classification.Classification;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.league.League;
import com.sss.garage.model.raceresult.RaceResultRepository;
import com.sss.garage.service.classification.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class SssClassificationService implements ClassificationService {
    private RaceResultRepository raceResultRepository;

    @Override
    public Page<Classification> getClassification(final League league, final Pageable pageable) {
        return setClassification(league, pageable);
    }

    private Page<Classification> setClassification(final League league, final Pageable pageable) {
        List<Classification> classifications = new ArrayList<>();
        for (Driver driver : raceResultRepository.findDriversByLeague(league)) {
            Classification classification = new Classification();
            classification.setDriver(driver);
            classification.setPoints(raceResultRepository.findPointsByDriverAndLeague(driver, league));
            classification.setP1Count(raceResultRepository.countFinishPositionByDriverAndLeague(driver, league, 1));
            classification.setP2Count(raceResultRepository.countFinishPositionByDriverAndLeague(driver, league, 2));
            classification.setP3Count(raceResultRepository.countFinishPositionByDriverAndLeague(driver, league, 3));
            classification.setP4Count(raceResultRepository.countFinishPositionByDriverAndLeague(driver, league, 4));
            classification.setP5Count(raceResultRepository.countFinishPositionByDriverAndLeague(driver, league, 5));
            classification.setP6Count(raceResultRepository.countFinishPositionByDriverAndLeague(driver, league, 6));
            classification.setP7Count(raceResultRepository.countFinishPositionByDriverAndLeague(driver, league, 7));
            classification.setP8Count(raceResultRepository.countFinishPositionByDriverAndLeague(driver, league, 8));
            classification.setP9Count(raceResultRepository.countFinishPositionByDriverAndLeague(driver, league, 9));
            classification.setP10Count(raceResultRepository.countFinishPositionByDriverAndLeague(driver, league, 10));
            classifications.add(classification);
        }
        classifications.sort(Comparator.comparing(Classification::getPoints)
                .thenComparing(Classification::getP1Count).thenComparing(Classification::getP2Count)
                .thenComparing(Classification::getP3Count).thenComparing(Classification::getP4Count)
                .thenComparing(Classification::getP5Count).thenComparing(Classification::getP6Count)
                .thenComparing(Classification::getP7Count).thenComparing(Classification::getP8Count)
                .thenComparing(Classification::getP9Count).thenComparing(Classification::getP10Count).reversed());
        return new PageImpl<>(classifications, pageable, classifications.size());
    }

    @Autowired
    public void setRaceResultRepository(RaceResultRepository raceResultRepository) {
        this.raceResultRepository = raceResultRepository;
    }
}
