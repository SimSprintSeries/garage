package com.sss.garage.converter.event;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.event.EventData;
import com.sss.garage.data.league.LeagueData;
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
        data.setName(source.getName());
        data.setStartDate(source.getStartDate());
        data.setLeague(getConversionService().convert(source.getLeague(), LeagueData.class));
    }
}
