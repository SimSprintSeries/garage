package com.sss.garage.converter.league;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.league.LeagueData;
import com.sss.garage.model.game.Game;
import com.sss.garage.model.league.League;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LeagueReversedConverter extends BaseConverter implements Converter<LeagueData, League> {

    @Override
    public League convert(final LeagueData source) {
        final League data = new League();

        data.setGame(getConversionService().convert(source.getGame(), Game.class));
        data.setId(source.getId());
        data.setName(source.getName());
        data.setPlatform(source.getPlatform());

        return data;
    }
}
