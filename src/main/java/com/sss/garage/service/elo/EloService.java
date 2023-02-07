package com.sss.garage.service.elo;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.elo.Elo;
import com.sss.garage.model.elo.CurrentEloRepository;
import com.sss.garage.model.elo.history.EloHistory;
import com.sss.garage.model.game.Game;
import com.sss.garage.model.race.Race;
import com.sss.garage.model.race.RaceRepository;
import com.sss.garage.model.raceresult.RaceResult;
import com.sss.garage.service.race.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EloService {
    private static final Integer DNF_DIVIDER = 2;
    private static final Integer POLE_BOOSTER = 3;
    private static final Integer LAP_BOOSTER = 3;
    private static final Integer K_FACTOR = 6;
    private static final Integer SPLIT_REDUCER = 1;

    private RaceService raceService;
    private CurrentEloRepository currentEloRepository;
    private RaceRepository raceRepository;

    public void calculateElo() {
        currentEloRepository.deleteAll();
        updateElo(raceService.getAllRacesSorted());
    }

    public void updateElo(List<Race> races) {
        for (Race race : races) {
            if (race.isTheOnlyScoredRacedInEvent()) {
                updateElo(race);
            }
            else {
                if (!raceService.isQuali(race)) {
                    updateElo(race.getParentRaceEvent());
                }
            }
        }
        raceRepository.saveAll(races);
    }

    public void updateElo(Race race) {
        final Game currentGame = race.getEvent().getLeague().getGame();
        final List<RaceResult> raceResults = new ArrayList<>(race.getRaceResults());

        Set<Elo> toUpdate = new HashSet<>();
        final Map<Driver, Integer> gameEloValuesSnapshot = new HashMap<>();
        final Map<Driver, Integer> gameFamilyEloValuesSnapshot = new HashMap<>();

        // data prep - maybe in a loop? Gets complicated but could impact performance
        for(final RaceResult raceResult : raceResults) {
            final Driver driver = raceResult.getDriver();
            final Elo gameElo = getDriverCurrentElo(currentGame, raceResult.getDriver());
            final Elo gameFamilyElo = getDriverCurrentElo(currentGame.getGameFamily(), raceResult.getDriver());

            toUpdate.add(gameElo);
            toUpdate.add(gameFamilyElo);

            gameEloValuesSnapshot.put(driver, gameElo.getValue());
            gameFamilyEloValuesSnapshot.put(driver, gameFamilyElo.getValue());

            currentEloRepository.save(new EloHistory(gameElo, race.getStartDate()));
            currentEloRepository.save(new EloHistory(gameFamilyElo, race.getStartDate()));
        }

        for(int i = 0; i < raceResults.size(); i++) {
            final RaceResult driverRaceResult = raceResults.get(i);
            final Driver driver = driverRaceResult.getDriver();
            final Elo currentDriverGameElo = getDriverCurrentElo(currentGame, driver);
            final Elo currentDriverGameFamilyElo = getDriverCurrentElo(currentGame.getGameFamily(), driver);

            for(int j = i + 1; j < raceResults.size(); j++) {
                final RaceResult opponentRaceResult = raceResults.get(j);
                final Driver opponent = opponentRaceResult.getDriver();
                final Elo opponentGameElo = getDriverCurrentElo(currentGame, opponent);
                final Elo opponentGameFamilyElo = getDriverCurrentElo(currentGame.getGameFamily(), opponent);

                updateElo(driverRaceResult, opponentRaceResult, currentDriverGameElo, gameEloValuesSnapshot);
                updateElo(driverRaceResult, opponentRaceResult, currentDriverGameFamilyElo, gameFamilyEloValuesSnapshot);

                updateElo(opponentRaceResult, driverRaceResult, opponentGameElo, gameEloValuesSnapshot);
                updateElo(opponentRaceResult, driverRaceResult, opponentGameFamilyElo, gameFamilyEloValuesSnapshot);
            }
        }

        race.setIsIncludedInElo(true);
        raceRepository.save(race);

        // race calculation finished, update
        currentEloRepository.saveAll(toUpdate);
    }

    public Elo getDriverCurrentElo(Game game, Driver driver) {
        return currentEloRepository.findByGameAndDriver(game, driver)
                .orElseGet(() -> new Elo(driver, game));
    }

    public void updateElo(RaceResult raceResultCurrentDriver, RaceResult raceResultOpponent, final Elo currentDriverNewElo, final Map<Driver, Integer> eloValuesSnapshot) {
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
    public void setRaceService(final RaceService raceService) {
        this.raceService = raceService;
    }

    @Autowired
    public void setEloRepository(final CurrentEloRepository currentEloRepository) {
        this.currentEloRepository = currentEloRepository;
    }

    @Autowired
    public void setRaceRepository(final RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

}
