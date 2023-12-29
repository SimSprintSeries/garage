package com.sss.garage.converter.team;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.entry.EntryData;
import com.sss.garage.data.game.GameData;
import com.sss.garage.data.team.TeamData;
import com.sss.garage.model.team.Team;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TeamConverter extends BaseConverter implements Converter<Team, TeamData> {
    @Override
    public TeamData convert(final Team source) {
        TeamData data = new TeamData();

        data.setId(source.getId());
        data.setName(source.getName());
        data.setGame(getConversionService().convert(source.getGame(), GameData.class));
        data.setEntry(source.getEntries().stream().map(e -> getConversionService().convert(e, EntryData.class))
                .collect(Collectors.toSet()));
        data.setColour(source.getColour());

        return data;
    }
}
