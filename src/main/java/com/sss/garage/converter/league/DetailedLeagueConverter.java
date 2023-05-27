package com.sss.garage.converter.league;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.event.EventData;
import com.sss.garage.data.league.DetailedLeagueData;
import com.sss.garage.data.split.SplitData;
import com.sss.garage.model.league.League;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DetailedLeagueConverter extends BaseConverter implements Converter<League, DetailedLeagueData> {

    private LeagueConverter leagueConverter;

    @Override
    public DetailedLeagueData convert(final League source) {
        final DetailedLeagueData league = new DetailedLeagueData(leagueConverter.convert(source));
        league.setEvents(source.getEvents().stream().map(e -> getConversionService().convert(e, EventData.class)).toList());
        league.setSplits(source.getSplits().stream().map(s -> getConversionService().convert(s, SplitData.class)).toList());
        league.setActive(source.getActive());
        league.setDiscordGroupId(source.getDiscordGroupId());
        return league;
    }

    @Autowired
    public void setLeagueConverter(final LeagueConverter leagueConverter) {
        this.leagueConverter = leagueConverter;
    }
}
