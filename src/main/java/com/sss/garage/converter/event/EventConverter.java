package com.sss.garage.converter.event;

import java.util.Optional;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.event.EventData;
import com.sss.garage.data.league.LeagueData;
import com.sss.garage.data.track.TrackData;
import com.sss.garage.model.event.Event;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EventConverter extends BaseConverter implements Converter<Event, EventData> {

    @Override
    public EventData convert(final Event source) {
        final EventData data = new EventData();
        convert(source, data);
        return data;
    }

    public void convert(final Event source, final EventData data) {
        data.setId(source.getId());
        data.setLeague(Optional.ofNullable(source.getLeague()).map(l -> getConversionService().convert(l, LeagueData.class)).orElse(null));
        //data.setDisplayText(data.getLeague() != null ? data.getLeague().getDisplayText() + " - " + source.getName() : source.getName());
        data.setDisplayText(source.getName());
        data.setStartDate(source.getStartDate());
        data.setTrack(getConversionService().convert(source.getTrack(), TrackData.class));
    }
}
