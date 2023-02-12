package com.sss.garage.service.elo.impl;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.elo.Elo;
import com.sss.garage.model.elo.CurrentEloRepository;
import com.sss.garage.model.game.Game;
import com.sss.garage.service.elo.EloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SssEloService implements EloService {

    private CurrentEloRepository currentEloRepository;

    @Override
    public List<Elo> getElos(final Game game) {
        return currentEloRepository.findByGame(game, Sort.by(Sort.Direction.DESC, "value"));
    }

    @Override
    public Elo getElo(Game game, Driver driver) {
        return currentEloRepository.findByGameAndDriver(game, driver)
                .orElseGet(() -> new Elo(driver, game));
    }

    @Override
    public void save(final Elo elo) {
        currentEloRepository.save(elo);
    }

    @Override
    public void deleteAll() {
        currentEloRepository.deleteAll();
    }

    @Autowired
    public void setCurrentEloRepository(final CurrentEloRepository currentEloRepository) {
        this.currentEloRepository = currentEloRepository;
    }
}
