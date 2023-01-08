package com.sss.garage.dev.initial.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

        gameRepository.saveAll(List.of(newGame("F122", f1), newGame("F121", f1)));

        final Driver driver1 = newDriver("test1");
        final Driver driver2 = newDriver("test2");
        driverRepository.saveAll(List.of(driver1, driver2));

        final Elo elo1 = newElo(1400);
        final Elo elo2 = newElo(1500);
        eloRepository.saveAll(List.of(elo1, elo2));

        final Event event1 = newEvent("event1", LocalDate.of(2020, 01, 18).atStartOfDay());
        final Event event2 = newEvent("event1", LocalDate.of(2021, 02, 19).atStartOfDay());
        eventRepository.saveAll(List.of(event1, event2));

        final League league1 = newLeague("PC");
        final League league2 = newLeague("PS");
        leagueRepository.saveAll(List.of(league1, league2));

        final RaceResult raceResult1 = newRaceResult(1, true, false, false);
        final RaceResult raceResult2 = newRaceResult(11, false, false, true);
        raceResultRepository.saveAll(List.of(raceResult1, raceResult2));

        final Split split1 = newSplit("A");
        final Split split2 = newSplit("B");
        splitRepository.saveAll(List.of(split1, split2));


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

    private Driver newDriver(final String name) {
        final Driver driver = new Driver();
        driver.setName(name);
        return driver;
    }

    private Elo newElo(final Integer eloValue) {
        final Elo elo = new Elo();
        elo.setEloValue(eloValue);
        return elo;
    }

    private Event newEvent(final String name, final LocalDateTime startDate) {
        final Event event = new Event();
        event.setName(name);
        event.setStartTime(startDate);
        return event;
    }

    private League newLeague(final String platform) {
        final League league = new League();
        league.setPlatform(platform);
        return league;
    }

    private RaceResult newRaceResult(final Integer finishPosition, final Boolean polePosition, final Boolean dnf, final Boolean fastestLap) {
        final RaceResult raceResult = new RaceResult();
        raceResult.setFinishPosition(finishPosition);
        raceResult.setPolePosition(polePosition);
        raceResult.setDnf(dnf);
        raceResult.setFastestLap(fastestLap);
        return raceResult;
    }

    private Split newSplit(final String leagueSplit) {
        final Split split = new Split();
        split.setSplit(leagueSplit);
        return split;
    }
}
