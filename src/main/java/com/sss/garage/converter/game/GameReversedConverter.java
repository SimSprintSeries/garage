package com.sss.garage.converter.game;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.game.GameData;
import com.sss.garage.model.game.Game;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GameReversedConverter extends BaseConverter implements Converter<GameData, Game> {

    @Override
    public Game convert(final GameData source) {
        final Game data = new Game();

        data.setId(source.getId());
        data.setName(source.getName());

        return data;
    }
}
