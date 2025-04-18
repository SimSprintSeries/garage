package com.sss.garage.converter.event;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.event.EventData;
import com.sss.garage.data.league.LeagueData;
import com.sss.garage.data.presence.PresenceData;
import com.sss.garage.data.race.RaceData;
import com.sss.garage.data.track.TrackData;
import com.sss.garage.model.event.Event;

import com.sss.garage.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class EventConverter extends BaseConverter implements Converter<Event, EventData> {

    private EventService eventService;

    @Override
    public EventData convert(final Event source) {
        final EventData data = new EventData();
        convert(source, data);
        return data;
    }

    public void convert(final Event source, final EventData data) {
        data.setId(source.getId());
        data.setLeague(Optional.ofNullable(source.getLeague()).map(l -> getConversionService().convert(l, LeagueData.class)).orElse(null));
        data.setDisplayText(source.getName());
        data.setPresences(source.getPresences().stream().map(p -> getConversionService().convert(p, PresenceData.class)).collect(Collectors.toSet()));
        data.setTrack(getConversionService().convert(source.getTrack(), TrackData.class));
        data.setRaces(source.getRaces().stream().map(r -> getConversionService().convert(r, RaceData.class)).collect(Collectors.toSet()));
        data.setStartDate(source.getStartDate());
        data.setCompleted(eventService.getCompletedPlayableEvents(source.getLeague(), Pageable.ofSize(1000)).toList().contains(source));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 7);
        data.setActiveForPresence(source.getStartDate().before(calendar.getTime()) && source.getStartDate().after(new Date()));
    }

    @Autowired
    public void setEventService(final EventService eventService) {
        this.eventService = eventService;
    }
}
