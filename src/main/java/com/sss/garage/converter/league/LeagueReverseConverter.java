package com.sss.garage.converter.league;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.league.LeagueData;
import com.sss.garage.model.game.Game;
import com.sss.garage.model.image.Image;
import com.sss.garage.model.image.ImageRepository;
import com.sss.garage.model.league.League;
import com.sss.garage.service.game.GameService;
import com.sss.garage.util.image.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LeagueReverseConverter extends BaseConverter implements Converter<LeagueData, League> {
    private GameService gameService;

    @Override
    public League convert(final LeagueData source) {
        final League target = new League();

        try {
            target.setGame(gameService.getGame(Long.valueOf(source.getGameId())).orElseThrow());
        } catch (NumberFormatException e) {
            target.setGame(getConversionService().convert(source.getGame(), Game.class));
        }
        target.setId(source.getId());
        target.setName(source.getName());
        target.setPlatform(source.getPlatform());
        target.setStartDate(source.getStartDate());
        target.setEventCount(source.getEventCount());

        return target;
    }

    @Autowired
    public void setGameService(final GameService gameService) {
        this.gameService = gameService;
    }
}
