package com.sss.garage.service.elo;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.elo.Elo;
import com.sss.garage.model.elo.CurrentEloRepository;
import com.sss.garage.model.elo.history.EloHistory;
import com.sss.garage.model.elo.history.EloHistoryRepository;
import com.sss.garage.model.game.Game;
import com.sss.garage.model.race.Race;
import com.sss.garage.model.race.RaceRepository;
import com.sss.garage.model.raceresult.RaceResult;
import com.sss.garage.service.race.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
    private EloHistoryRepository eloHistoryRepository;

    public void calculateElo() {
        currentEloRepository.deleteAll();
        updateElo(raceService.getAllRacesSorted());
        //updateEloSince(raceService.getAllRacesSorted().get(700));
    }

    public void updateElo(List<Race> races) {
        races.stream()
                .filter(Predicate.not(Race::isIncludedInElo))
                .filter(r -> r.getContainedRaces().isEmpty())
                .filter(r -> !raceService.isQuali(r))
                .forEach(this::updateElo);
    }

    public void updateEloSince(final Race race) {
        List<Race> racesSince = raceRepository.findByStartDateGreaterThanEqual(race.getStartDate(), Sort.by(Sort.Direction.ASC, "startDate"));
        for (Race raceSince : racesSince) {
            for (RaceResult raceResult : raceSince.getRaceResults()) {
                currentEloRepository.saveAll(eloHistoryRepository.findByStartDateAndDriver(race.getStartDate(), raceResult.getDriver()));
            }
        }
        updateElo(racesSince);
    }

    public List<Elo> getElos(final Game game) {
        return currentEloRepository.findByGame(game, Sort.by(Sort.Direction.DESC, "value"))
                .stream().collect(Collectors.toList());
    }

    public List<Elo> getEloHistories(final Game game, final Driver driver) {
        return currentEloRepository.findHistoryByGameAndDriver(game, driver, Sort.by(Sort.Direction.ASC, "startDate"))
                .stream().toList();
    }

    private Elo getElo(Game game, Driver driver) {
        return currentEloRepository.findByGameAndDriver(game, driver)
                .orElseGet(() -> new Elo(driver, game));
    }

    private void updateElo(Race race) {
        Game currentGame;
        try {
            currentGame = race.getEvent().getLeague().getGame();
        } catch (NullPointerException e) {
            currentGame = race.getParentRaceEvent().getEvent().getLeague().getGame();
        }
        final List<RaceResult> raceResults = new ArrayList<>(race.getRaceResults());

        Set<Elo> toUpdate = new HashSet<>();
        final Map<Driver, Integer> gameEloValuesSnapshot = new HashMap<>();
        final Map<Driver, Integer> gameFamilyEloValuesSnapshot = new HashMap<>();

        // data prep - maybe in a loop? Gets complicated but could impact performance
        for(final RaceResult raceResult : raceResults) {
            final Driver driver = raceResult.getDriver();
            final Elo gameElo = getElo(currentGame, raceResult.getDriver());
            final Elo gameFamilyElo = getElo(currentGame.getGameFamily(), raceResult.getDriver());

            List<EloHistory> eloHistories = new ArrayList<>(eloHistoryRepository.findHistoryByDriver
                    (driver, Sort.by(Sort.Direction.ASC, "validUntil")));
            EloHistory previousEloHistory = null;
            EloHistory previousEloHistoryFamily = null;
            if (eloHistories.size() != 0) {
                previousEloHistory = eloHistories.get(eloHistories.size() - 2);
                previousEloHistoryFamily = eloHistories.get(eloHistories.size() - 1);
            }

            toUpdate.add(gameElo);
            toUpdate.add(gameFamilyElo);

            gameEloValuesSnapshot.put(driver, gameElo.getValue());
            gameFamilyEloValuesSnapshot.put(driver, gameFamilyElo.getValue());

            currentEloRepository.save(new EloHistory(gameElo, race.getStartDate(), race, previousEloHistory));
            currentEloRepository.save(new EloHistory(gameFamilyElo, race.getStartDate(), race, previousEloHistoryFamily));
        }

        for(int i = 0; i < raceResults.size(); i++) {
            final RaceResult driverRaceResult = raceResults.get(i);
            final Driver driver = driverRaceResult.getDriver();
            final Elo currentDriverGameElo = getElo(currentGame, driver);
            final Elo currentDriverGameFamilyElo = getElo(currentGame.getGameFamily(), driver);

            for(int j = i + 1; j < raceResults.size(); j++) {
                final RaceResult opponentRaceResult = raceResults.get(j);
                final Driver opponent = opponentRaceResult.getDriver();
                final Elo opponentGameElo = getElo(currentGame, opponent);
                final Elo opponentGameFamilyElo = getElo(currentGame.getGameFamily(), opponent);

                updateElo(driverRaceResult, opponentRaceResult, currentDriverGameElo, gameEloValuesSnapshot);
                updateElo(driverRaceResult, opponentRaceResult, currentDriverGameFamilyElo, gameFamilyEloValuesSnapshot);

                updateElo(opponentRaceResult, driverRaceResult, opponentGameElo, gameEloValuesSnapshot);
                updateElo(opponentRaceResult, driverRaceResult, opponentGameFamilyElo, gameFamilyEloValuesSnapshot);
            }
        }

        race.setIncludedInElo(true);
        raceRepository.save(race);

        // race calculation finished, update
        currentEloRepository.saveAll(toUpdate);
    }

    private void updateElo(RaceResult raceResultCurrentDriver, RaceResult raceResultOpponent, final Elo currentDriverNewElo, final Map<Driver, Integer> eloValuesSnapshot) {
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

    @Autowired
    public void setEloHistoryRepository(final EloHistoryRepository eloHistoryRepository) {
        this.eloHistoryRepository = eloHistoryRepository;
    }

}
