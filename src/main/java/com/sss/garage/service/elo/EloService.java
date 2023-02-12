package com.sss.garage.service.elo;

import java.util.List;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.elo.Elo;
import com.sss.garage.model.game.Game;

public interface EloService {
    List<Elo> getElos(final Game game);
    Elo getElo(Game game, Driver driver);
    void deleteAll();
    void save(final Elo elo);
}
