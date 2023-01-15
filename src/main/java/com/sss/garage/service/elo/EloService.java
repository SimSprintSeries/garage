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

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class EloService {

    private static final Integer DNF_DIVIDER = 2;
    private static final Integer POLE_BOOSTER = 3;
    private static final Integer LAP_BOOSTER = 3;
    private static final Integer K_FACTOR = 6;
    private static final Double ELO_OVER_TIME_CHANGE_FACTOR = -0.001;

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
            for (final Race race : gameMap.get(game)) {
                if (!race.getRaceResults().isEmpty()) {
                    updateElo(race);
                }
            }
        }
    }
    public void updateElo(Race race) {
        long daysDiff = DAYS.between(race.getEvent().getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());
        Game game = race.getEvent().getLeague().getGame();

        for (RaceResult raceResultCurrentDriver : race.getRaceResults()) {
            Driver currentDriver = raceResultCurrentDriver.getDriver();
            Elo eloCurrentDriver = eloRepository.findByGameAndDriver(game, currentDriver)
                    .orElseGet(() -> new Elo(currentDriver, game));
            Elo eloCurrentDriverFamily = eloRepository.findByGameAndDriver(game.getGameFamily(), currentDriver)
                    .orElseGet(() -> new Elo(currentDriver, game.getGameFamily()));

            int eloDiff = 0;
            int eloDiffFamily = 0;

            for (RaceResult raceResultOpponent : race.getRaceResults()) {
                Driver opponent = raceResultOpponent.getDriver();

                if (!currentDriver.equals(opponent)) {
                    Elo eloOpponent = eloRepository.findByGameAndDriver(game, opponent)
                            .orElseGet(() -> new Elo(opponent, game));
                    Elo eloOpponentFamily = eloRepository.findByGameAndDriver(game.getGameFamily(), opponent)
                            .orElseGet(() -> new Elo(opponent, game.getGameFamily()));

                    boolean currentDriverBetter = raceResultCurrentDriver.getFinishPosition() < raceResultOpponent.getFinishPosition();

                    int eloPart = calculate2PlayersRating(eloCurrentDriver.getValue(), eloOpponent.getValue(), currentDriverBetter);
                    int eloPartFamily = calculate2PlayersRating(eloCurrentDriverFamily.getValue(), eloOpponentFamily.getValue(), currentDriverBetter);

                    eloDiff += eloPart - eloCurrentDriver.getValue();
                    eloDiffFamily += eloPartFamily - eloCurrentDriverFamily.getValue();
                }
            }

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

            double dateFraction = ELO_OVER_TIME_CHANGE_FACTOR * daysDiff + 1;

            eloCurrentDriver.setValue(eloCurrentDriver.getValue() + eloDiff);
            eloCurrentDriver.setValue((int)(eloCurrentDriverFamily.getValue() + (Math.round(eloDiffFamily * dateFraction))));

            eloRepository.save(eloCurrentDriver);
            eloRepository.save(eloCurrentDriverFamily);
        }

    }
    private static int calculate2PlayersRating(int currentDriverRating, int opponentRating, boolean outcome) {

        double actualScore;

        if (outcome) {
            actualScore = 1.0;
        }
        else {
            actualScore = 0;
        }

        double exponent = (double) (opponentRating - currentDriverRating) / 400;

        double expectedOutcome = (1 / (1 + (Math.pow(10, exponent))));

        return (int) Math.round(currentDriverRating + K_FACTOR * (actualScore - expectedOutcome));
    }
}
