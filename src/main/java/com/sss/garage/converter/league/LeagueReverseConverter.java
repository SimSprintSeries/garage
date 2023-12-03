package com.sss.garage.converter.league;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.league.LeagueData;
import com.sss.garage.model.game.Game;
import com.sss.garage.model.league.League;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LeagueReverseConverter extends BaseConverter implements Converter<LeagueData, League> {

    @Override
    public League convert(final LeagueData source) {
        final League target = new League();

        target.setGame(getConversionService().convert(source.getGame(), Game.class));
        target.setId(source.getId());
        target.setName(source.getName());
        target.setPlatform(source.getPlatform());
        target.setStartDate(source.getStartDate());
        target.setEventCount(source.getEventCount());

        return target;
    }
}
