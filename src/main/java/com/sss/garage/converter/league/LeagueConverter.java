package com.sss.garage.converter.league;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.game.GameData;
import com.sss.garage.data.league.LeagueData;
import com.sss.garage.model.event.EventRepository;
import com.sss.garage.model.league.League;

import com.sss.garage.model.race.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
public class LeagueConverter extends BaseConverter implements Converter<League, LeagueData> {

    private RaceRepository raceRepository;

    private EventRepository eventRepository;

    @Override
    public LeagueData convert(final League source) {
        final LeagueData data = new LeagueData();

        data.setId(source.getId());
        data.setName(source.getName());
        data.setDisplayText(source.getName());
        data.setPlatform(source.getPlatform());
        data.setGame(getConversionService().convert(source.getGame(), GameData.class));
        data.setStartDate(findStartDate(source));
        data.setEventCount(eventRepository.countByLeague(source));
        data.setBanner(source.getBanner());
        data.setLogo(source.getLogo());

        return data;
    }

    private String findStartDate(final League league) {
        LocalDate date = raceRepository.findFirstByLeagueOrderByStartDateAsc(league).getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }

    @Autowired
    public void setRaceRepository(final RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Autowired
    public void setEventRepository(final EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
}
