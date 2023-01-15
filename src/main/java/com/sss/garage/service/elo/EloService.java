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

            Elo eloCurrentDriver = eloRepository.findByGameAndDriver(game, currentDriver).orElseGet(() -> new Elo(currentDriver.getId(), 1500, currentDriver, game));

            int eloDiff = 0;

            Elo eloCurrentDriverFamily = eloRepository.findByGameAndDriver(game.getGameFamily(), currentDriver).orElseGet(() -> new Elo(currentDriver.getId(), 1500, currentDriver, game.getGameFamily()));

            int eloDiffFamily = 0;

            for (RaceResult raceResultOpponent : race.getRaceResults()) {

                Driver opponent = raceResultOpponent.getDriver();

                if (currentDriver != opponent) {

                    Elo eloOpponent = eloRepository.findByGameAndDriver(game, opponent).orElseGet(() -> new Elo(opponent.getId(), 1500, opponent, game));

                    Elo eloOpponentFamily = eloRepository.findByGameAndDriver(game.getGameFamily(), opponent).orElseGet(() -> new Elo(opponent.getId(), 1500, opponent, game.getGameFamily()));

                    if (raceResultCurrentDriver.getFinishPosition() < raceResultOpponent.getFinishPosition()) {

                        int eloPart = calculate2PlayersRating(eloCurrentDriver.getValue(), eloOpponent.getValue(), "+");

                        eloDiff += eloPart - eloCurrentDriver.getValue();

                        int eloPartFamily = calculate2PlayersRating(eloCurrentDriverFamily.getValue(), eloOpponentFamily.getValue(), "+");

                        eloDiffFamily += eloPartFamily - eloCurrentDriverFamily.getValue();
                    }
                    else if (raceResultCurrentDriver.getFinishPosition() > raceResultOpponent.getFinishPosition()) {

                        int eloPart = calculate2PlayersRating(eloCurrentDriver.getValue(), eloOpponent.getValue(), "-");

                        eloDiff += eloPart - eloCurrentDriver.getValue();

                        int eloPartFamily = calculate2PlayersRating(eloCurrentDriverFamily.getValue(), eloOpponentFamily.getValue(), "-");

                        eloDiffFamily += eloPartFamily - eloCurrentDriverFamily.getValue();
                    }
                }
            }

            if (raceResultCurrentDriver.getDnf()) {
                eloDiff = eloDiff / 2;
                eloDiffFamily = eloDiffFamily / 2;
            }

            if (raceResultCurrentDriver.getPolePosition()) {
                eloDiff += 3;
                eloDiffFamily += 3;
            }

            if (raceResultCurrentDriver.getFastestLap()) {
                eloDiff += 3;
                eloDiffFamily += 3;
            }

            int newElo = eloCurrentDriver.getValue() + eloDiff;

            double dateFraction = -0.001 * daysDiff + 1;

            int newEloFamily = (int)(eloCurrentDriverFamily.getValue() + (Math.round(eloDiffFamily * dateFraction)));

            eloCurrentDriver.setValue(newElo);

            eloRepository.save(eloCurrentDriver);

            eloCurrentDriver.setValue(newEloFamily);

            eloRepository.save(eloCurrentDriverFamily);
        }
    }
    private static int calculate2PlayersRating(int player1Rating, int player2Rating, String outcome) {

        double actualScore;

        if (outcome.equals("+")) {
            actualScore = 1.0;
        }
        else if (outcome.equals("-")) {
            actualScore = 0;
        }
        else {
            return player1Rating;
        }

        double exponent = (double) (player2Rating - player1Rating) / 400;

        double expectedOutcome = (1 / (1 + (Math.pow(10, exponent))));

        return (int) Math.round(player1Rating + 6 * (actualScore - expectedOutcome));
    }
}
