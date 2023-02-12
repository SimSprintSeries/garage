package com.sss.garage.service.game;

import com.sss.garage.model.game.Game;
import com.sss.garage.model.race.Race;

public interface GameService {
    Game getGame(final Race race);
}
