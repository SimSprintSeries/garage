package com.sss.garage.converter.race;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.converter.event.EventConverter;
import com.sss.garage.data.race.RaceData;
import com.sss.garage.data.split.SplitData;
import com.sss.garage.model.race.Race;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RaceConverter extends BaseConverter implements Converter<Race, RaceData> {

    private EventConverter eventConverter;

    @Override
    public RaceData convert(final Race source) {
        RaceData data = new RaceData();
        eventConverter.convert(source, data);

        data.setSplit(getConversionService().convert(source.getSplit(), SplitData.class));
        //data.setDisplayText(data.getSplit().getDisplayText() + " - " + source.getName());
        data.setDisplayText(source.getName());

        return data;
    }

    @Autowired
    public void setEventConverter(final EventConverter eventConverter) {
        this.eventConverter = eventConverter;
    }
}
