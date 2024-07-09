package com.sss.garage.facade.racepointdictionary.impl;

import com.sss.garage.data.racepointdictionary.RacePointDictionaryData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.racepointdictionary.RacePointDictionaryFacade;
import com.sss.garage.model.racepointdictionary.RacePointDictionary;
import com.sss.garage.service.racepointdictionary.RacePointDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SssRacePointDictionaryFacade extends SssBaseFacade implements RacePointDictionaryFacade {
    private RacePointDictionaryService racePointDictionaryService;

    @Override
    public Page<RacePointDictionaryData> getRacePointDictionaries(final Pageable pageable) {
        Page<RacePointDictionary> racePointDictionaries = racePointDictionaryService.getRacePointDictionaries(pageable);
        return racePointDictionaries.map(p -> conversionService.convert(p, RacePointDictionaryData.class));
    }

    @Override
    public void createRacePointDictionary(final RacePointDictionaryData racePointDictionary) {
        racePointDictionaryService.createRacePointDictionary(conversionService.convert(racePointDictionary, RacePointDictionary.class));
    }

    @Autowired
    public void setRacePointDictionaryService(final RacePointDictionaryService racePointDictionaryService) {
        this.racePointDictionaryService = racePointDictionaryService;
    }
}
