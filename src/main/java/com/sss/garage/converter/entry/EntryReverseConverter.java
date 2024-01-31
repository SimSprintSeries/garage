package com.sss.garage.converter.entry;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.entry.EntryData;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.entry.Entry;
import com.sss.garage.model.game.Game;
import com.sss.garage.model.team.Team;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EntryReverseConverter extends BaseConverter implements Converter<EntryData, Entry> {
    @Override
    public Entry convert(final EntryData source) {
        Entry target = new Entry();

        target.setId(source.getId());
        target.setDriver(getConversionService().convert(source.getDriver(), Driver.class));
        target.setTeams(source.getTeam().stream().map(t -> getConversionService().convert(t, Team.class))
                .collect(Collectors.toSet()));
        target.setPreferredPartner(source.getPreferredPartner());
        target.setGame(getConversionService().convert(source.getGame(), Game.class));

        return target;
    }
}
