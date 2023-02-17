package com.sss.garage.facade.elo.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import com.sss.garage.data.elo.EloData;
import com.sss.garage.facade.elo.EloFacade;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.elo.Elo;
import com.sss.garage.model.game.Game;
import com.sss.garage.model.race.Race;
import com.sss.garage.model.raceresult.RaceResult;
import com.sss.garage.service.driver.DriverService;
import com.sss.garage.service.elo.EloCalculationService;
import com.sss.garage.service.elo.EloHistoryService;
import com.sss.garage.service.elo.EloService;
import com.sss.garage.service.game.GameService;
import com.sss.garage.service.race.RaceService;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SssEloFacade implements EloFacade {

    private EloService eloService;
    private EloHistoryService eloHistoryService;
    private EloCalculationService eloCalculationService;
    private RaceService raceService;
    private GameService gameService;
    private ConversionService conversionService;
    private DriverService driverService;

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
    public void updateEloSince(final Long raceId) {
        final Race race = raceService.findById(raceId)
                .orElseThrow();

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
                    final Elo elo = eloService.getEloWithDefault(game, driver);
                    elo.setValue(previousElo.getValue());
                    eloService.save(elo);
                    eloHistoryService.deleteAllHistoryAfterIncluding(previousElo);
                });
    }

    @Override
    public Page<EloData> getElosPaginated(final String gameId, final Pageable pageable) {
        Page<Elo> elos;
        if(Strings.isNotEmpty(gameId)) {
            final Game game = gameService.getGame(Long.valueOf(gameId)).orElseThrow();
            elos = eloService.getElos(game, pageable);
        }
        else {
            elos = eloService.getElos(pageable);
        }
        return elos.map(e -> conversionService.convert(e, EloData.class));
    }

    @Override
    public Optional<EloData> getElo(final String driverId, final String gameId) {
        final Driver driver = driverService.getDriver(Long.valueOf(driverId)).orElseThrow();
        final Game game = gameService.getGame(Long.valueOf(gameId)).orElseThrow();
        return eloService.getElo(game, driver)
                .map(e -> conversionService.convert(e, EloData.class));
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

    @Autowired
    public void setConversionService(final ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Autowired
    public void setDriverService(final DriverService driverService) {
        this.driverService = driverService;
    }
}
