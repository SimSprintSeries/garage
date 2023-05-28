package com.sss.garage.service.game;

import java.util.List;
import java.util.Optional;

import com.sss.garage.model.game.Game;
import com.sss.garage.model.race.Race;

public interface GameService {
    Game getGame(final Race race);
    Optional<Game> getGame(final Long id);
    List<Game> getAllGames();
    void createGame(final Game game);
    void deleteGame(final Long id);
}
