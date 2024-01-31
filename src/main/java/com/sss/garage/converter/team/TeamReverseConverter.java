package com.sss.garage.converter.team;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.team.TeamData;
import com.sss.garage.model.entry.Entry;
import com.sss.garage.model.game.Game;
import com.sss.garage.model.team.Team;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TeamReverseConverter extends BaseConverter implements Converter<TeamData, Team> {
    @Override
    public Team convert(final TeamData source) {
        Team target = new Team();

        target.setId(source.getId());
        target.setName(source.getName());
        target.setGame(getConversionService().convert(source.getGame(), Game.class));
        target.setEntries(source.getEntry().stream().map(e -> getConversionService().convert(e, Entry.class))
                .collect(Collectors.toSet()));
        target.setColour(source.getColour());

        return target;
    }
}
