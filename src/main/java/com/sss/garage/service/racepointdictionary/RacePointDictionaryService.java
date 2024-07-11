package com.sss.garage.service.racepointdictionary;

import com.sss.garage.model.racepointdictionary.RacePointDictionary;
import com.sss.garage.model.racepointtype.RacePointType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RacePointDictionaryService {
    RacePointDictionary getRacePointDictionaryForRacePointType(final RacePointType racePointType);

    Page<RacePointDictionary> getRacePointDictionaries(Pageable pageable);
}
