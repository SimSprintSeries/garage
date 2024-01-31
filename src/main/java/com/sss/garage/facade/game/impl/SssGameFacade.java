package com.sss.garage.facade.game.impl;

import java.util.List;

import com.sss.garage.data.game.GameData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.game.GameFacade;
import com.sss.garage.model.game.Game;
import com.sss.garage.service.game.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SssGameFacade extends SssBaseFacade implements GameFacade {

    private GameService gameService;

    @Override
    public GameData getGame(final Long id) {
        return gameService.getGame(id)
                .map(g -> conversionService.convert(g, GameData.class))
                .orElseThrow();
    }

    @Override
    public void createGame(final GameData gameData) {
        gameService.createGame(conversionService.convert(gameData, Game.class));
    }

    @Override
    public void deleteGame(final Long id) {
        gameService.deleteGame(id);
    }

    @Override
    public Page<GameData> getGamesPaginated(final Pageable pageable) {
        Page<Game> game = gameService.getGamesPaginated(pageable);
        return game.map(g -> conversionService.convert(g, GameData.class));
    }

    @Autowired
    public void setGameService(final GameService gameService) {
        this.gameService = gameService;
    }
}
