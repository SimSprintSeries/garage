package com.sss.garage.converter.track;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.event.EventData;
import com.sss.garage.data.track.TrackData;
import com.sss.garage.model.track.Track;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TrackConverter extends BaseConverter implements Converter<Track, TrackData> {

    @Override
    public TrackData convert(final Track source) {
        final TrackData data = new TrackData();
        data.setName(source.getName());
        data.setCountry(source.getCountry());
        return data;
    }
}
