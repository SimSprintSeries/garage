package com.sss.garage.converter.racepointdictionary;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.racepointdictionary.RacePointDictionaryData;
import com.sss.garage.model.racepointdictionary.RacePointDictionary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RacePointDictionaryReverseConverter extends BaseConverter implements Converter<RacePointDictionaryData, RacePointDictionary> {
    @Override
    public RacePointDictionary convert(final RacePointDictionaryData source) {
        final RacePointDictionary target = new RacePointDictionary();

        target.setPoints(source.getPoints());
        target.setRacePointType(source.getRacePointType());
        target.setFastestLapPoints(source.getFastestLapPoints());
        target.setPolePositionPoints(source.getPolePositionPoints());
        target.setFastestLapForTop10(source.getFastestLapForTop10());
        target.setFastestLapScored(source.getFastestLapScored());
        target.setPolePositionScored(source.getPolePositionScored());

        return target;
    }
}
