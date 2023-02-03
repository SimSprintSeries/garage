package com.sss.garage.service.elo;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.elo.Elo;
import com.sss.garage.model.elo.EloRepository;
import com.sss.garage.model.game.Game;
import com.sss.garage.model.game.GameRepository;
import com.sss.garage.model.race.Race;
import com.sss.garage.model.league.League;
import com.sss.garage.model.event.Event;
import com.sss.garage.model.race.RaceRepository;
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
    private static final Integer SPLIT_REDUCER = 1;

    private GameRepository gameRepository;

    @Autowired
    public void setGameRepository(final GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    private RaceRepository raceRepository;

    @Autowired
    public void setRaceRepository(final RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    private EloRepository eloRepository;

    @Autowired
    public void setEloRepository(final EloRepository eloRepository) {
        this.eloRepository = eloRepository;
    }

    public void calculateElo() {
        List<Race> races = raceRepository.findAll();

        Collections.sort(races, Comparator.comparing(Event::getStartDate));

        updateElo(races);
    }

    public void recalculateElo() {
        eloRepository.deleteAll();
        calculateElo();
    }

    public void updateElo(List<Race> races) {
        for (Race race : races) {
            if (race.getEvent() != null) {
                updateElo(race);
            }
            else {
                if (!race.getName().equals("Kwalifikacje")) {
                    updateElo(race.getParentRaceEvent());
                }
            }
        }
    }

    public void updateElo(Race race) {
        Set<Elo> toUpdate = new HashSet<>();

        for (RaceResult raceResultCurrentDriver : race.getRaceResults()) {
            for (RaceResult raceResultOpponent : race.getRaceResults()) {
                if (!raceResultCurrentDriver.equals(raceResultOpponent)) {
                    updateElo(race.getEvent().getLeague().getGame(), raceResultCurrentDriver, raceResultOpponent, toUpdate);
                    updateElo(race.getEvent().getLeague().getGame().getGameFamily(), raceResultCurrentDriver, raceResultOpponent, toUpdate);
                }
            }
        }
        eloRepository.saveAll(toUpdate);
    }

    private Optional<Elo> findByDriverAndGame(final Driver driver, final Game game, final Set<Elo> elos) {
        return elos.stream()
                .filter(e -> e.getDriver().equals(driver))
                .filter(e -> e.getGame().equals(game))
                .findFirst();
    }

    public Elo getDriverElo(Game game, Driver driver, Set<Elo> toUpdate) {
        return eloRepository.findByGameAndDriver(game, driver)
                .or(() -> findByDriverAndGame(driver, game, toUpdate))
                .orElseGet(() -> new Elo(driver, game));
    }

    public void updateElo(Game game, RaceResult raceResultCurrentDriver, RaceResult raceResultOpponent, Set<Elo> toUpdate) {
        Driver currentDriver = raceResultCurrentDriver.getDriver();
        Driver opponent = raceResultOpponent.getDriver();

        Integer eloDiff = 0;

        Elo eloCurrentDriver = getDriverElo(game, currentDriver, toUpdate);
        Elo eloOpponent = getDriverElo(game, opponent, toUpdate);

        Boolean outcome = raceResultCurrentDriver.getFinishPosition() < raceResultOpponent.getFinishPosition();

        Integer eloNew = calculate2PlayersRating(eloCurrentDriver.getValue(), eloOpponent.getValue(), outcome);

        eloDiff += eloNew - eloCurrentDriver.getValue();

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

        eloCurrentDriver.setValue(eloCurrentDriver.getValue() + eloDiff);

        toUpdate.add(eloCurrentDriver);
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
