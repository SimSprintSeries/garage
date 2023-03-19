package com.sss.garage.facade.game.impl;

import java.util.List;

import com.sss.garage.data.game.GameData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.game.GameFacade;
import com.sss.garage.service.game.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
public class SssGameFacade extends SssBaseFacade implements GameFacade {

    private GameService gameService;

    @Override
    public List<GameData> getAllGames() {
        return gameService.getAllGames().stream()
                .map(g -> conversionService.convert(g, GameData.class))
                .toList();
    }

    @Autowired
    public void setGameService(final GameService gameService) {
        this.gameService = gameService;
    }
}
