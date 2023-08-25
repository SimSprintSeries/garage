package com.sss.garage.service.raceresult.impl;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.race.Race;
import com.sss.garage.model.raceresult.RaceResult;
import com.sss.garage.model.raceresult.RaceResultRepository;
import com.sss.garage.service.raceresult.RaceResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<RaceResult> getRaceResultsPaginated(final String finishPosition, final Boolean polePosition, final Boolean dnf, final Boolean dsq
            , final Boolean fastestLap, final Driver driver, final Race race, final Pageable pageable) {
        return raceResultRepository.findAllByParams(finishPosition, polePosition, dnf, dsq, fastestLap, driver, race, pageable);
    }

    @Autowired
    public void setRaceResultRepository(RaceResultRepository raceResultRepository) {
        this.raceResultRepository = raceResultRepository;
    }
}
