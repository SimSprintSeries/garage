package com.sss.garage.service.raceresult.impl;

import com.sss.garage.model.raceresult.RaceResult;
import com.sss.garage.model.raceresult.RaceResultRepository;
import com.sss.garage.service.raceresult.RaceResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SssRaceResultService implements RaceResultService {

    private RaceResultRepository raceResultRepository;

    @Override
    public List<RaceResult> getAllRaceResults() {
        return raceResultRepository.findAll();
    }

    @Override
    public Optional<RaceResult> getRaceResult(final Long id) {
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

    @Autowired
    public void setRaceResultRepository(RaceResultRepository raceResultRepository) {
        this.raceResultRepository = raceResultRepository;
    }
}
