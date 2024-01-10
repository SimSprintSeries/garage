package com.sss.garage.service.classification.impl;

import com.sss.garage.model.classification.Classification;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.league.League;
import com.sss.garage.model.raceresult.RaceResult;
import com.sss.garage.model.raceresult.RaceResultRepository;
import com.sss.garage.service.classification.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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
            classifications.add(classification);
        }
        for(Classification c : classifications) {
            System.out.println(c.getDriver().getName() + c.getPoints());
        }
        classifications.sort(Comparator.comparing(Classification::getPoints).reversed());
        return new PageImpl<>(classifications, pageable, classifications.size());
    }

    @Autowired
    public void setRaceResultRepository(RaceResultRepository raceResultRepository) {
        this.raceResultRepository = raceResultRepository;
    }
}
