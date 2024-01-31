package com.sss.garage.service.racepointdictionary.impl;

import com.sss.garage.model.racepointdictionary.RacePointDictionary;
import com.sss.garage.model.racepointdictionary.RacePointDictionaryRepository;
import com.sss.garage.model.racepointtype.RacePointType;
import com.sss.garage.service.racepointdictionary.RacePointDictionaryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SssRacePointDictionaryService implements RacePointDictionaryService {

    private RacePointDictionaryRepository racePointDictionaryRepository;

    @Override
    public RacePointDictionary getRacePointDictionaryForRacePointType(final RacePointType racePointType) {
        return racePointDictionaryRepository.findByRacePointType(racePointType)
                .orElseThrow();
    }

    @Autowired
    public void setRacePointDictionaryRepository(final RacePointDictionaryRepository racePointDictionaryRepository) {
        this.racePointDictionaryRepository = racePointDictionaryRepository;
    }
}
