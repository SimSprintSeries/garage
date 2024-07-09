package com.sss.garage.facade.racepointdictionary;

import com.sss.garage.data.racepointdictionary.RacePointDictionaryData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RacePointDictionaryFacade {
    Page<RacePointDictionaryData> getRacePointDictionaries(final Pageable pageable);

    void createRacePointDictionary(final RacePointDictionaryData racePointDictionary);
}
