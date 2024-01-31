package com.sss.garage.service.racepointdictionary;

import com.sss.garage.model.racepointdictionary.RacePointDictionary;
import com.sss.garage.model.racepointtype.RacePointType;

public interface RacePointDictionaryService {
    RacePointDictionary getRacePointDictionaryForRacePointType(final RacePointType racePointType);
}
