package com.sss.garage.converter.game;

import java.util.Optional;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.game.GameData;
import com.sss.garage.model.game.Game;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GameConverter extends BaseConverter implements Converter<Game, GameData> {

    @Override
    public GameData convert(final Game source) {
        final GameData game = new GameData();

        game.setId(source.getId());
        game.setName(source.getName());

        Optional.ofNullable(source.getGameFamily())
                .ifPresent(g -> game.setGameFamily(getConversionService().convert(g, GameData.class)));

        return game;
    }
}
