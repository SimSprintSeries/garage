package com.sss.garage.service.race.impl;

import java.util.List;

import com.sss.garage.model.race.Race;
import com.sss.garage.model.race.RaceRepository;
import com.sss.garage.service.race.RaceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SssRaceService implements RaceService {

    private RaceRepository raceRepository;

    @Override
    public List<Race> getAllRacesSorted() {
        return raceRepository.findAll(Sort.by(Sort.Direction.ASC, "startDate"));
    }

    @Override
    public Boolean isQuali(final Race race) {
        return race.getName().equals("Kwalifikacje");
    }

    @Autowired
    public void setRaceRepository(final RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }
}
