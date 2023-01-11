package com.sss.garage.service;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.elo.Elo;
import com.sss.garage.model.elo.EloRepository;
import com.sss.garage.model.game.Game;
import com.sss.garage.model.game.GameRepository;
import com.sss.garage.model.game.family.GameFamilyRepository;
import com.sss.garage.model.race.Race;
import com.sss.garage.model.league.League;
import com.sss.garage.model.event.Event;
import com.sss.garage.model.raceresult.RaceResult;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class eloService {

    private GameRepository gameRepository;
    private GameFamilyRepository gameFamilyRepository;

    private EloRepository eloRepository;

    public void calculateElo() {

        String[] games = {"F1 22", "F1 2021", "F1 2020", "F1 2019", "AC", "ACC"};

        List<List<Race>> raceNestedList = new ArrayList<>();

        for (final String gameName : games) {
            List<Race> raceList = gameRepository.findByName(gameName).orElse(null).getLeagues().stream()
                    .map(League::getEvents)
                    .flatMap(Collection::stream)
                    .map(Event::getRaces)
                    .flatMap(Collection::stream)
                    .filter(r -> !r.getContainedRaces().isEmpty())
                    .sorted(Comparator.comparing(Race::getStartDate))
                    .toList();
            raceNestedList.add(raceList);
        }

        String[] gamesFamilies = {"F1", "AC"};

        List<List<Race>> raceFamilyNestedList = new ArrayList<>();

        for (final String gameFamilyName : games) {
            List<Race> raceFamilyList = gameFamilyRepository.findByName(gameFamilyName).orElse(null).getLeagues().stream()
                    .map(League::getEvents)
                    .flatMap(Collection::stream)
                    .map(Event::getRaces)
                    .flatMap(Collection::stream)
                    .filter(r -> !r.getContainedRaces().isEmpty())
                    .sorted(Comparator.comparing(Race::getStartDate))
                    .toList();
            raceFamilyNestedList.add(raceFamilyList);
        }

        for(final List<Race> raceList : raceNestedList) {
            for (final Race race : raceList) {
                int count = race.getRaceResults().size();
                if (count != 0) {
                    updateElo(race);
                }
            }
        }

        for(final List<Race> raceFamilyList : raceFamilyNestedList) {
            for (final Race race : raceFamilyList) {
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
            Elo eloA = eloRepository.findAllByGame(race.getEvent().getLeague().getGame()).orElse(null);
            int eloDiff = 0;
            for (RaceResult raceResultB : race.getRaceResults()) {
                Driver driverB = raceResultB.getDriver();
                Elo eloB = eloRepository.findAllByGame(race.getEvent().getLeague().getGame()).orElse(null);
                if (raceResultA.getFinishPosition() < raceResultB.getFinishPosition()) {
                    int eloPart = calculate2PlayersRating(eloA, eloB, "+");
                    eloDiff += eloPart - eloA;
                }
                else if (raceResultA.getFinishPosition() > raceResultB.getFinishPosition()) {
                    int eloPart = calculate2PlayersRating(eloA, eloB, "-");
                    eloDiff += eloPart - eloA;
                }
            }
            if (raceResultA.getDnf()) {
                eloDiff = eloDiff / 2;
            }
            if (raceResultA.getPolePosition()) {
                eloDiff += 3;
            }
            if (raceResultA.getFastestLap()) {
                eloDiff += 3;
            }
            double dateFraction = -0.001 * daysDiff + 1;
            int newElo = (int)(eloA + (Math.round(eloDiff * dateFraction)));
        }

    }
    private static int calculate2PlayersRating(int player1Rating, int player2Rating, String outcome) {
        double actualScore;
        if (outcome.equals("+")) {
            actualScore = 1.0;
        } else if (outcome.equals("-")) {
            actualScore = 0;
        } else {
            return player1Rating;
        }
        double exponent = (double) (player2Rating - player1Rating) / 400;
        double expectedOutcome = (1 / (1 + (Math.pow(10, exponent))));
        int K = 6;
        int newRating = (int) Math.round(player1Rating + K * (actualScore - expectedOutcome));
        return newRating;
    }
}
