package com.sss.garage.service.elo;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.elo.Elo;
import com.sss.garage.model.elo.EloRepository;
import com.sss.garage.model.game.Game;
import com.sss.garage.model.game.GameRepository;
import com.sss.garage.model.race.Race;
import com.sss.garage.model.league.League;
import com.sss.garage.model.event.Event;
import com.sss.garage.model.raceresult.RaceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EloService {

    private static final Integer DNF_DIVIDER = 2;
    private static final Integer POLE_BOOSTER = 3;
    private static final Integer LAP_BOOSTER = 3;
    private static final Integer K_FACTOR = 6;

    private GameRepository gameRepository;

    @Autowired
    public void setGameRepository(final GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    private EloRepository eloRepository;

    @Autowired
    public void setEloRepository(final EloRepository eloRepository) {
        this.eloRepository = eloRepository;
    }

    public void calculateElo() {
        List<Game> games = gameRepository.findAll();
        Map<Game, List<Race>> gameMap = new HashMap<>();

        for (final Game game : games) {
            List<Race> raceList = game.getLeagues().stream()
                    .map(League::getEvents)
                    .flatMap(Collection::stream)
                    .map(Event::getRaces)
                    .flatMap(Collection::stream)
                    .filter(r -> r.getContainedRaces().isEmpty())
                    .sorted(Comparator.comparing(Race::getStartDate))
                    .toList();

            gameMap.put(game, raceList);
        }

        for(final Game game : gameMap.keySet()) {
            List<Race> races = gameMap.get(game);
            if (!races.isEmpty()) {
                updateElo(races);
            }
        }
    }

    public void recalculateElo() {
        eloRepository.deleteAll();
        calculateElo();
    }

    public void updateElo(List<Race> races) {
        for (Race race : races) {
            updateElo(race);
        }
    }

    public void updateElo(Race race) {
        for (RaceResult raceResultCurrentDriver : race.getRaceResults()) {
            for (RaceResult raceResultOpponent : race.getRaceResults()) {
                if (!raceResultCurrentDriver.equals(raceResultOpponent)) {
                    updateElo(race, raceResultCurrentDriver, raceResultOpponent);
                }
            }
        }
    }

    public void updateElo(Race race, RaceResult raceResultCurrentDriver, RaceResult raceResultOpponent) {

        Game game = race.getEvent().getLeague().getGame();

        Driver currentDriver = raceResultCurrentDriver.getDriver();
        Driver opponent = raceResultOpponent.getDriver();

        Elo eloCurrentDriver = eloRepository.findByGameAndDriver(game, currentDriver)
                .orElseGet(() -> new Elo(currentDriver, game));
        Elo eloCurrentDriverFamily = eloRepository.findByGameAndDriver(game.getGameFamily(), currentDriver)
                .orElseGet(() -> new Elo(currentDriver, game.getGameFamily()));

        Integer eloDiff = 0;
        Integer eloDiffFamily = 0;

        Elo eloOpponent = eloRepository.findByGameAndDriver(game, opponent)
                .orElseGet(() -> new Elo(opponent, game));
        Elo eloOpponentFamily = eloRepository.findByGameAndDriver(game.getGameFamily(), opponent)
                .orElseGet(() -> new Elo(opponent, game.getGameFamily()));

        Boolean outcome = raceResultCurrentDriver.getFinishPosition() < raceResultOpponent.getFinishPosition();

        Integer eloNew = calculate2PlayersRating(eloCurrentDriver.getValue(), eloOpponent.getValue(), outcome);
        Integer eloNewFamily = calculate2PlayersRating(eloCurrentDriverFamily.getValue(), eloOpponentFamily.getValue(), outcome);

        eloDiff += eloNew - eloCurrentDriver.getValue();
        eloDiffFamily += eloNewFamily - eloCurrentDriverFamily.getValue();

        if (raceResultCurrentDriver.getDnf()) {
            eloDiff = eloDiff / DNF_DIVIDER;
            eloDiffFamily = eloDiffFamily / DNF_DIVIDER;
        }

        if (raceResultCurrentDriver.getPolePosition()) {
            eloDiff += POLE_BOOSTER;
            eloDiffFamily += POLE_BOOSTER;
        }

        if (raceResultCurrentDriver.getFastestLap()) {
            eloDiff += LAP_BOOSTER;
            eloDiffFamily += LAP_BOOSTER;
        }

        eloCurrentDriver.setValue(eloCurrentDriver.getValue() + eloDiff);
        eloCurrentDriver.setValue(eloCurrentDriverFamily.getValue() + eloDiffFamily);

        eloRepository.save(eloCurrentDriver);
        eloRepository.save(eloCurrentDriverFamily);
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
}
