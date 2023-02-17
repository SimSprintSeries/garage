package com.sss.garage.service.elo.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.elo.Elo;
import com.sss.garage.model.elo.EloRepository;
import com.sss.garage.model.elo.history.EloHistory;
import com.sss.garage.model.game.Game;
import com.sss.garage.model.race.Race;
import com.sss.garage.model.raceresult.RaceResult;
import com.sss.garage.service.elo.EloCalculationService;
import com.sss.garage.service.elo.EloService;
import com.sss.garage.service.game.GameService;
import com.sss.garage.service.race.RaceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SssEloCalculationService implements EloCalculationService {
    private static final Integer DNF_DIVIDER = 2;
    private static final Integer POLE_BOOSTER = 3;
    private static final Integer LAP_BOOSTER = 3;
    private static final Integer K_FACTOR = 6;
    private static final Integer SPLIT_REDUCER = 1;

    private GameService gameService;
    private RaceService raceService;
    private EloRepository eloRepository;
    private EloService eloService;

    @Override
    public void calculateAndSave(final Race race) {
        final Game currentGame = gameService.getGame(race);
        final List<RaceResult> raceResults = race.getRaceResults().stream().sorted(Comparator.comparing(RaceResult::getFinishPosition)).toList();

        Set<Elo> toUpdate = new HashSet<>();
        final Map<Driver, Integer> gameEloValuesSnapshot = new HashMap<>();
        final Map<Driver, Integer> gameFamilyEloValuesSnapshot = new HashMap<>();

        final List<EloHistory> historyToSave = new ArrayList<>();

        // data prep - maybe in a loop? Gets complicated but could impact performance
        for(final RaceResult raceResult : raceResults) {
            final Driver driver = raceResult.getDriver();
            final Elo gameElo = eloService.getEloWithDefault(currentGame, driver);
            final Elo gameFamilyElo = eloService.getEloWithDefault(currentGame.getGameFamily(), driver);

            toUpdate.add(gameElo);
            toUpdate.add(gameFamilyElo);

            gameEloValuesSnapshot.put(driver, gameElo.getValue());
            gameFamilyEloValuesSnapshot.put(driver, gameFamilyElo.getValue());

            historyToSave.add(new EloHistory(gameElo, race.getStartDate(), race));
            historyToSave.add(new EloHistory(gameFamilyElo, race.getStartDate(), race));
        }
        eloRepository.saveAll(historyToSave);

        for(int i = 0; i < raceResults.size(); i++) {
            final RaceResult driverRaceResult = raceResults.get(i);
            final Driver driver = driverRaceResult.getDriver();
            final Elo currentDriverGameElo = eloService.getEloWithDefault(currentGame, driver);
            final Elo currentDriverGameFamilyElo = eloService.getEloWithDefault(currentGame.getGameFamily(), driver);

            for(int j = i + 1; j < raceResults.size(); j++) {
                final RaceResult opponentRaceResult = raceResults.get(j);
                final Driver opponent = opponentRaceResult.getDriver();
                final Elo opponentGameElo = eloService.getEloWithDefault(currentGame, opponent);
                final Elo opponentGameFamilyElo = eloService.getEloWithDefault(currentGame.getGameFamily(), opponent);

                calculateElo(driverRaceResult, opponentRaceResult, currentDriverGameElo, gameEloValuesSnapshot);
                calculateElo(driverRaceResult, opponentRaceResult, currentDriverGameFamilyElo, gameFamilyEloValuesSnapshot);

                calculateElo(opponentRaceResult, driverRaceResult, opponentGameElo, gameEloValuesSnapshot);
                calculateElo(opponentRaceResult, driverRaceResult, opponentGameFamilyElo, gameFamilyEloValuesSnapshot);
            }
        }

        race.setIncludedInElo(true);
        raceService.save(race);

        // race calculation finished, update
        eloRepository.saveAll(toUpdate);
    }

    private void calculateElo(RaceResult raceResultCurrentDriver, RaceResult raceResultOpponent, final Elo currentDriverNewElo, final Map<Driver, Integer> eloValuesSnapshot) {
        Integer eloValueOpponent = eloValuesSnapshot.get(raceResultOpponent.getDriver());

        Integer newValue = calculate2PlayersRating(currentDriverNewElo.getValue(), eloValueOpponent
                , raceResultCurrentDriver.getFinishPosition() < raceResultOpponent.getFinishPosition());

        Integer eloDiff = newValue - currentDriverNewElo.getValue();

        if (raceResultCurrentDriver.getDnf()) {
            eloDiff = eloDiff / DNF_DIVIDER;
        }

        if (raceResultCurrentDriver.getPolePosition()) {
            eloDiff += POLE_BOOSTER;
        }

        if (raceResultCurrentDriver.getFastestLap()) {
            eloDiff += LAP_BOOSTER;
        }

        if (raceResultCurrentDriver.getRace().getSplit().getSplit().equals("B")) {
            eloDiff -= SPLIT_REDUCER;
        }

        else if (raceResultCurrentDriver.getRace().getSplit().getSplit().equals("C")) {
            eloDiff -= SPLIT_REDUCER * 2;
        }

        currentDriverNewElo.setValue(currentDriverNewElo.getValue() + eloDiff);
    }

    private static Integer calculate2PlayersRating(Integer currentDriverRating, Integer opponentRating, Boolean currentDriverBetter) {

        double actualScore;

        if (currentDriverBetter) {
            actualScore = 1.0;
        }
        else {
            actualScore = 0;
        }

        Double exponent = (double) (opponentRating - currentDriverRating) / 400;

        Double expectedOutcome = (1 / (1 + (Math.pow(10, exponent))));

        return (int) Math.round(currentDriverRating + K_FACTOR * (actualScore - expectedOutcome));
    }

    @Autowired
    public void setGameService(final GameService gameService) {
        this.gameService = gameService;
    }

    @Autowired
    public void setRaceService(final RaceService raceService) {
        this.raceService = raceService;
    }

    @Autowired
    public void setEloRepository(final EloRepository eloRepository) {
        this.eloRepository = eloRepository;
    }

    @Autowired
    public void setEloService(final EloService eloService) {
        this.eloService = eloService;
    }
}
