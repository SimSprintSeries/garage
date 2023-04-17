package com.sss.garage.converter.game;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.game.GameData;
import com.sss.garage.model.game.Game;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GameReverseConverter extends BaseConverter implements Converter<GameData, Game> {

    @Override
    public Game convert(final GameData source) {
        final Game target = new Game();

        target.setId(source.getId());
        target.setName(source.getName());

        return target;
    }
}
