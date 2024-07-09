package com.sss.garage.converter.racepointdictionary;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.racepointdictionary.RacePointDictionaryData;
import com.sss.garage.model.racepointdictionary.RacePointDictionary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RacePointDictionaryConverter extends BaseConverter implements Converter<RacePointDictionary, RacePointDictionaryData> {
    @Override
    public RacePointDictionaryData convert(final RacePointDictionary source) {
        final RacePointDictionaryData data = new RacePointDictionaryData();

        data.setId(source.getId());
        data.setPoints(source.getPoints());
        data.setRacePointType(source.getRacePointType());
        data.setFastestLapPoints(source.getFastestLapPoints());
        data.setPolePositionPoints(source.getPolePositionPoints());
        data.setFastestLapForTop10(source.getFastestLapForTop10());
        data.setFastestLapScored(source.getFastestLapScored());
        data.setPolePositionScored(source.getPolePositionScored());

        return data;
    }
}
