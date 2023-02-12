package com.sss.garage.facade.elo.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import com.sss.garage.facade.elo.EloFacade;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.elo.Elo;
import com.sss.garage.model.game.Game;
import com.sss.garage.model.race.Race;
import com.sss.garage.model.raceresult.RaceResult;
import com.sss.garage.service.elo.EloCalculationService;
import com.sss.garage.service.elo.EloHistoryService;
import com.sss.garage.service.elo.EloService;
import com.sss.garage.service.game.GameService;
import com.sss.garage.service.race.RaceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SssEloFacade implements EloFacade {

    private EloService eloService;
    private EloHistoryService eloHistoryService;
    private EloCalculationService eloCalculationService;
    private RaceService raceService;
    private GameService gameService;

    @Override
    public void calculateElo() {
        eloService.deleteAll();
        raceService.saveAll(raceService.getAllRacesSorted().stream()
                .peek(r -> r.setIncludedInElo(false))
                .toList());

        updateElo(raceService.getAllRacesSorted());
    }

    @Override
    public void updateElo() {
        updateElo(raceService.getAllRacesNotIncludedInEloSorted());
    }

    @Override
    public void updateElo(List<Race> races) {
        races.stream()
                .filter(Predicate.not(Race::getIncludedInElo))
                .filter(r -> r.getContainedRaces().isEmpty())
                .filter(r -> !raceService.isQuali(r))
                .forEach(eloCalculationService::calculateAndSave);
    }

    @Override
    public void updateEloSince(final Race race) {
        // TODO: what if race is not yet calculated (and previous races!)
        final List<Race> racesSince = raceService.getGameSortedRacesSince(race);
        final Set<Driver> alreadyUpdated = new HashSet<>();

        for (Race raceSince : racesSince) {
            raceSince.setIncludedInElo(false);

            for (RaceResult raceResult : raceSince.getRaceResults()) {
                final Driver driver = raceResult.getDriver();

                if(!alreadyUpdated.contains(driver)) { // breaks if we've got more than one game in races
                    backupEloToBeforeRace(driver, raceResult);
                    alreadyUpdated.add(driver);
                }
            }
        }
        updateElo(racesSince);
    }

    private void backupEloToBeforeRace(final Driver driver, final RaceResult raceResult) {
        final Game eloGame = gameService.getGame(raceResult.getRace());

        backupEloToBeforeRace(driver, raceResult, eloGame);
        backupEloToBeforeRace(driver, raceResult, eloGame.getGameFamily());
    }

    private void backupEloToBeforeRace(final Driver driver, final RaceResult raceResult, final Game game) {
        raceResult.getRace().getEloHistories().stream()
                .filter(r -> r.getDriver().equals(driver))
                .filter(r -> r.getGame().equals(game))
                .findFirst()
                .ifPresent(previousElo -> {
                    final Elo elo = eloService.getElo(game, driver);
                    elo.setValue(previousElo.getValue());
                    eloService.save(elo);
                    eloHistoryService.deleteAllHistoryAfterIncluding(previousElo);
                });
    }

    @Autowired
    public void setEloService(final EloService eloService) {
        this.eloService = eloService;
    }

    @Autowired
    public void setEloHistoryService(final EloHistoryService eloHistoryService) {
        this.eloHistoryService = eloHistoryService;
    }

    @Autowired
    public void setEloCalculationService(final EloCalculationService eloCalculationService) {
        this.eloCalculationService = eloCalculationService;
    }

    @Autowired
    public void setRaceService(final RaceService raceService) {
        this.raceService = raceService;
    }

    @Autowired
    public void setGameService(final GameService gameService) {
        this.gameService = gameService;
    }
}
