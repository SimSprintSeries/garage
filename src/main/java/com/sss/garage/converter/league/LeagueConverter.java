package com.sss.garage.converter.league;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.game.GameData;
import com.sss.garage.data.league.LeagueData;
import com.sss.garage.model.league.League;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LeagueConverter extends BaseConverter implements Converter<League, LeagueData> {

    @Override
    public LeagueData convert(final League source) {
        final LeagueData data = new LeagueData();

        data.setId(source.getId());
        data.setName(source.getName());
        data.setDisplayText(source.getName());
        data.setPlatform(source.getPlatform());
        data.setGame(getConversionService().convert(source.getGame(), GameData.class));

        return data;
    }
}
