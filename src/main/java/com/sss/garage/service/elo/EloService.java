package com.sss.garage.service.elo;

import java.util.List;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.elo.Elo;
import com.sss.garage.model.game.Game;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EloService {
    Page<Elo> getElos(final Game game, final Pageable pageable);
    Page<Elo> getElos(final Pageable pageable);
    Elo getElo(Game game, Driver driver);
    void deleteAll();
    void save(final Elo elo);
}
