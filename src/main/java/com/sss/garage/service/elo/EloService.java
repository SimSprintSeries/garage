package com.sss.garage.service.elo;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.elo.Elo;
import com.sss.garage.model.elo.EloRepository;
import com.sss.garage.model.game.Game;
import com.sss.garage.model.game.GameRepository;
import com.sss.garage.model.game.family.GameFamily;
import com.sss.garage.model.game.family.GameFamilyRepository;
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

    private GameFamilyRepository gameFamilyRepository;

    @Autowired
    public void setFamilyRepository(final GameFamilyRepository gameFamilyRepository) {
        this.gameFamilyRepository = gameFamilyRepository;
    }

    private EloRepository eloRepository;

    @Autowired
    public void setEloRepository(final EloRepository eloRepository) {
        this.eloRepository = eloRepository;
    }

    public void calculateElo() {

        String[] games = {"F1 22", "F1 2021", "F1 2020", "F1 2019", "AC", "ACC"};

        Map<Game, List<Race>> gameMap = new HashMap<>();

        for (final String gameName : games) {
            Game game = gameRepository.findByName(gameName).orElse(null);
            assert game != null;
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

        String[] gamesFamilies = {"F1", "AC"};

        Map<Game, List<Race>> gameFamilyMap = new HashMap<>();

        for (final String gameFamilyName : gamesFamilies) {
            Game gameFamily = gameFamilyRepository.findByName(gameFamilyName).orElse(null);
            assert gameFamily != null;
            List<Race> raceFamilyList = gameFamily.getLeagues().stream()
                    .map(League::getEvents)
                    .flatMap(Collection::stream)
                    .map(Event::getRaces)
                    .flatMap(Collection::stream)
                    .filter(r -> !r.getContainedRaces().isEmpty())
                    .sorted(Comparator.comparing(Race::getStartDate))
                    .toList();

            gameFamilyMap.put(gameFamily, raceFamilyList);
        }

        for(final Game game : gameMap.keySet()) {

            for (final Race race : gameMap.get(game)) {

                int count = race.getRaceResults().size();

                if (count != 0) {
                    updateElo(race);
                }
            }
        }
    }
    public void updateElo(Race race) {

        LocalDate raceDate = race.getEvent().getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long daysDiff = DAYS.between(raceDate, LocalDate.now());

        for (RaceResult raceResultA : race.getRaceResults()) {

            Driver driverA = raceResultA.getDriver();

            Game game = race.getEvent().getLeague().getGame();

            Elo eloA = eloRepository.findByGameAndDriver(race.getEvent().getLeague().getGame(),
                    driverA).orElseGet(() -> {
                Elo eloDriverA = new Elo();

                eloDriverA.setDriver(driverA);

                eloDriverA.setGame(game);

                eloDriverA.setEloValue(1500);
                return eloDriverA;
            });

            int eloDiff = 0;

            Elo eloAFamily = eloRepository.findByGameAndDriver(race.getEvent().getLeague().getGame()
                            .getGameFamily(), driverA).orElseGet(() -> {
                Elo eloDriverA = new Elo();

                eloDriverA.setDriver(driverA);

                eloDriverA.setGame(game.getGameFamily());

                eloDriverA.setEloValue(1500);
                return eloDriverA;
            });

            int eloDiffFamily = 0;

            for (RaceResult raceResultB : race.getRaceResults()) {

                Driver driverB = raceResultB.getDriver();

                if (driverA != driverB) {

                    Elo eloB = eloRepository.findByGameAndDriver(game, driverB).orElseGet(() -> {
                        Elo eloDriverA = new Elo();

                        eloDriverA.setDriver(driverA);

                        eloDriverA.setGame(game);

                        eloDriverA.setEloValue(1500);
                        return eloDriverA;
                    });

                    Elo eloBFamily = eloRepository.findByGameAndDriver(race.getEvent().getLeague().getGame()
                            .getGameFamily(), driverB).orElseGet(() -> {
                        Elo eloDriverA = new Elo();

                        eloDriverA.setDriver(driverA);

                        eloDriverA.setGame(game.getGameFamily());

                        eloDriverA.setEloValue(1500);
                        return eloDriverA;
                    });

                    if (raceResultA.getFinishPosition() < raceResultB.getFinishPosition()) {

                        int eloPart = calculate2PlayersRating(eloA.getEloValue(), eloB.getEloValue(), "+");

                        eloDiff += eloPart - eloA.getEloValue();

                        int eloPartFamily = calculate2PlayersRating(eloAFamily.getEloValue(), eloBFamily.getEloValue(), "+");

                        eloDiffFamily += eloPartFamily - eloAFamily.getEloValue();
                    }
                    else if (raceResultA.getFinishPosition() > raceResultB.getFinishPosition()) {

                        int eloPart = calculate2PlayersRating(eloA.getEloValue(), eloB.getEloValue(), "-");

                        eloDiff += eloPart - eloA.getEloValue();

                        int eloPartFamily = calculate2PlayersRating(eloAFamily.getEloValue(), eloBFamily.getEloValue(), "-");

                        eloDiffFamily += eloPartFamily - eloAFamily.getEloValue();
                    }
                }
            }

            if (raceResultA.getDnf()) {
                eloDiff = eloDiff / 2;
                eloDiffFamily = eloDiffFamily / 2;
            }

            if (raceResultA.getPolePosition()) {
                eloDiff += 3;
                eloDiffFamily += 3;
            }

            if (raceResultA.getFastestLap()) {
                eloDiff += 3;
                eloDiffFamily += 3;
            }

            int newElo = eloA.getEloValue() + eloDiff;

            double dateFraction = -0.001 * daysDiff + 1;

            int newEloFamily = (int)(eloAFamily.getEloValue() + (Math.round(eloDiffFamily * dateFraction)));

            eloA.setEloValue(newElo);
            eloAFamily.setEloValue(newEloFamily);

            eloRepository.save(eloA);
            eloRepository.save(eloAFamily);
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

        int K = 6;

        int newRating = (int) Math.round(player1Rating + K * (actualScore - expectedOutcome));

        return newRating;
    }
}
