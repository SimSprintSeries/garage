package com.sss.garage.facade.game;

import java.util.List;

import com.sss.garage.data.game.GameData;

public interface GameFacade {
    List<GameData> getAllGames();
    GameData getGame(final Long id);
    void createGame(final GameData gameData);
    void deleteGame(final Long id);
}
