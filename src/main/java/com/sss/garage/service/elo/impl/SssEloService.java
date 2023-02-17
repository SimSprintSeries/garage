package com.sss.garage.service.elo.impl;

import java.util.Optional;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.elo.Elo;
import com.sss.garage.model.elo.CurrentEloRepository;
import com.sss.garage.model.game.Game;
import com.sss.garage.service.elo.EloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SssEloService implements EloService {

    private CurrentEloRepository currentEloRepository;

    @Override
    public Page<Elo> getElos(final Game game, final Pageable pageable) {
        return currentEloRepository.findAllByGame(game, pageable);
    }

    @Override
    public Page<Elo> getElos(final Pageable pageable) {
        return currentEloRepository.findAll(pageable);
    }

    @Override
    public Elo getEloWithDefault(Game game, Driver driver) {
        return getElo(game, driver).orElseGet(() -> {
                    final Elo elo = new Elo(driver, game);
                    currentEloRepository.save(elo);
                    return elo;
                });
    }

    public Optional<Elo> getElo(Game game, Driver driver) {
        return currentEloRepository.findByGameAndDriver(game, driver);
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
