package com.sss.garage.converter.event;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.event.EventData;
import com.sss.garage.model.event.Event;
import com.sss.garage.model.league.League;
import com.sss.garage.model.track.Track;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EventReverseConverter extends BaseConverter implements Converter<EventData, Event> {

    @Override
    public Event convert(final EventData source) {
        final Event target = new Event();
        convert(source, target);
        return target;
    }

    public void convert(final EventData source, final Event target) {
        target.setId(source.getId());
        target.setLeague(Optional.ofNullable(source.getLeague()).map(e -> getConversionService().convert(e, League.class)).orElse(null));
        target.setStartDate(source.getStartDate());
        target.setTrack(getConversionService().convert(source.getTrack(), Track.class));
        target.setActiveForPresence(source.getActiveForPresence());
    }
}
