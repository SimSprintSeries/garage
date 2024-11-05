package com.sss.garage.converter.race;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.presence.PresenceData;
import com.sss.garage.data.race.RaceData;
import com.sss.garage.data.split.SplitData;
import com.sss.garage.data.track.TrackData;
import com.sss.garage.model.race.Race;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RaceConverter extends BaseConverter implements Converter<Race, RaceData> {

    @Override
    public RaceData convert(final Race source) {
        RaceData data = new RaceData();

        data.setId(source.getId());
        data.setSplit(getConversionService().convert(source.getSplit(), SplitData.class));
        data.setDisplayText(source.getEvent().getName() + " - " + source.getName());
        data.setTrack(getConversionService().convert(source.getEvent().getTrack(), TrackData.class));
        data.setEventId(source.getEvent().getId());

        return data;
    }
}
