package com.sss.garage.converter.entry;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.driver.DriverData;
import com.sss.garage.data.entry.EntryData;
import com.sss.garage.data.game.GameData;
import com.sss.garage.data.team.TeamData;
import com.sss.garage.model.entry.Entry;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EntryConverter extends BaseConverter implements Converter<Entry, EntryData> {
    @Override
    public EntryData convert(final Entry source) {
        EntryData data = new EntryData();

        data.setId(source.getId());
        data.setDriverData(getConversionService().convert(source.getDriver(), DriverData.class));
        data.setTeamData(source.getTeams().stream().map(t -> getConversionService().convert(t, TeamData.class))
                .collect(Collectors.toSet()));
        data.setPreferredPartner(source.getPreferredPartner());
        data.setGameData(getConversionService().convert(source.getGame(), GameData.class));

        return data;
    }
}
