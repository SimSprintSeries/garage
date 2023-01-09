package com.sss.garage.dev.initial.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.driver.DriverRepository;
import com.sss.garage.model.elo.Elo;
import com.sss.garage.model.elo.EloRepository;
import com.sss.garage.model.event.Event;
import com.sss.garage.model.event.EventRepository;
import com.sss.garage.model.game.Game;
import com.sss.garage.model.game.GameRepository;
import com.sss.garage.model.game.family.GameFamily;
import com.sss.garage.model.game.family.GameFamilyRepository;
import com.sss.garage.model.league.League;
import com.sss.garage.model.league.LeagueRepository;
import com.sss.garage.model.raceresult.RaceResult;
import com.sss.garage.model.raceresult.RaceResultRepository;
import com.sss.garage.model.split.Split;
import com.sss.garage.model.split.SplitRepository;
import com.sss.garage.model.user.DiscordUser;
import com.sss.garage.model.user.UserRepository;
import com.sss.garage.service.discord.api.DiscordApiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataLoader {

    private final DiscordApiService discordApiService;

    @Autowired
    public DataLoader(final UserRepository userRepository,
                      final GameFamilyRepository gameFamilyRepository,
                      final GameRepository gameRepository,
                      final DiscordApiService discordApiService,
                      final DriverRepository driverRepository,
                      final EloRepository eloRepository,
                      final EventRepository eventRepository,
                      final LeagueRepository leagueRepository,
                      final RaceResultRepository raceResultRepository,
                      final SplitRepository splitRepository) {
        this.discordApiService = discordApiService;
        loadInitialData(userRepository, gameFamilyRepository, gameRepository, driverRepository, eloRepository, eventRepository,
                leagueRepository, raceResultRepository, splitRepository);
    }

    public void loadInitialData(final UserRepository userRepository, final GameFamilyRepository gameFamilyRepository, GameRepository gameRepository,
                                final DriverRepository driverRepository, final EloRepository eloRepository, final EventRepository eventRepository,
                                final LeagueRepository leagueRepository, final RaceResultRepository raceResultRepository, final SplitRepository splitRepository) {
        discordApiService.persistAllRoles();
        userRepository.save(newUser("507654703568519168")); //Rychu Peja solo

        final GameFamily f1 = newGameFamily("F1");
        gameFamilyRepository.save(f1);

        final Game game1 = newGame("F122", f1);
        final Game game2 = newGame("F121", f1);
        gameRepository.saveAll(List.of(game1, game2));

        userRepository.saveAll(List.of(newUser("12345"), newUser("54321")));
        final Driver driver1 = newDriver("test1", newUser("12345"));
        final Driver driver2 = newDriver("test2", newUser("54321"));
        driverRepository.saveAll(List.of(driver1, driver2));

        final Elo elo1 = newElo(1400, driver1, game1);
        final Elo elo2 = newElo(1500, driver2, game2);
        eloRepository.saveAll(List.of(elo1, elo2));

        final League league1 = newLeague("PC", game1);
        final League league2 = newLeague("PS", game2);
        leagueRepository.saveAll(List.of(league1, league2));

        final Split split1 = newSplit("A", league1);
        final Split split2 = newSplit("B", league1);
        splitRepository.saveAll(List.of(split1, split2));

        final Event event1 = newEvent("event1", LocalDate.of(2020, 01, 18).atStartOfDay(), league1);
        final Event event2 = newEvent("event2", LocalDate.of(2021, 02, 19).atStartOfDay(), league2);
        eventRepository.saveAll(List.of(event1, event2));

        final RaceResult raceResult1 = newRaceResult(1, true, false, false, driver1, event1, split1);
        final RaceResult raceResult2 = newRaceResult(11, false, false, true, driver2, event2, split2);
        raceResultRepository.saveAll(List.of(raceResult1, raceResult2));
    }

    private DiscordUser newUser(final String id) {
        final DiscordUser user = new DiscordUser();
        user.setId(id);
        return user;
    }

    private GameFamily newGameFamily(final String name) {
        final GameFamily gameFamily = new GameFamily();
        gameFamily.setName(name);
        return gameFamily;
    }

    private Game newGame(final String name, final GameFamily gameFamily) {
        final Game game = new Game();
        game.setName(name);
        game.setGameFamily(gameFamily);
        return game;
    }

    private Driver newDriver(final String name, final DiscordUser discordUser) {
        final Driver driver = new Driver();
        driver.setName(name);
        driver.setDiscordUser(discordUser);
        return driver;
    }

    private Elo newElo(final Integer eloValue, final Driver driver, final Game game) {
        final Elo elo = new Elo();
        elo.setEloValue(eloValue);
        elo.setDriver(driver);
        elo.setGame(game);
        return elo;
    }

    private Event newEvent(final String name, final LocalDateTime startDate, final League league) {
        final Event event = new Event();
        event.setName(name);
        event.setStartTime(startDate);
        event.setLeague(league);
        return event;
    }

    private League newLeague(final String platform, final Game game) {
        final League league = new League();
        league.setPlatform(platform);
        league.setGame(game);
        return league;
    }

    private RaceResult newRaceResult(final Integer finishPosition, final Boolean polePosition, final Boolean dnf, final Boolean fastestLap,
                                     final Driver driver, final Event event, final Split split) {
        final RaceResult raceResult = new RaceResult();
        raceResult.setFinishPosition(finishPosition);
        raceResult.setPolePosition(polePosition);
        raceResult.setDnf(dnf);
        raceResult.setFastestLap(fastestLap);
        raceResult.setDriver(driver);
        raceResult.setEvent(event);
        raceResult.setSplit(split);
        return raceResult;
    }

    private Split newSplit(final String leagueSplit, final League league) {
        final Split split = new Split();
        split.setSplit(leagueSplit);
        split.setLeague(league);
        return split;
    }
}
