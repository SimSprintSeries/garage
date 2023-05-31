package com.sss.garage.converter.track;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.track.TrackData;
import com.sss.garage.model.track.Track;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TrackReverseConverter extends BaseConverter implements Converter<TrackData, Track> {

    @Override
    public Track convert(final TrackData source) {
        final Track target = new Track();

        target.setId(source.getId());
        target.setName(source.getName());
        target.setCountry(source.getCountry());

        return target;
    }
}
