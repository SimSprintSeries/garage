package com.sss.garage.dev.initial.data;

import java.io.IOException;
import java.util.Date;

import com.sss.garage.dev.initial.data.legacy.LegacyDataImporter;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.elo.Elo;
import com.sss.garage.model.event.Event;
import com.sss.garage.model.game.Game;
import com.sss.garage.model.game.family.GameFamily;
import com.sss.garage.model.league.League;
import com.sss.garage.model.race.RaceRepository;
import com.sss.garage.model.raceresult.RaceResult;
import com.sss.garage.model.split.Split;
import com.sss.garage.model.user.DiscordUser;
import com.sss.garage.service.discord.api.DiscordApiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataLoader {

    private final DiscordApiService discordApiService;

    private final LegacyDataImporter legacyDataImporter;

    private final RaceRepository raceRepository;

    @Autowired
    public DataLoader(final DiscordApiService discordApiService,
            final LegacyDataImporter legacyDataImporter,
                      final RaceRepository raceRepository) {
        this.discordApiService = discordApiService;
        this.legacyDataImporter = legacyDataImporter;
        this.raceRepository = raceRepository;
        loadInitialData();
    }

    public void loadInitialData() {
        discordApiService.persistAllRoles();

        // simple check to not import twice
        if (raceRepository.count() != 0) {
            return;
        }

        try {
            legacyDataImporter.importLegacyData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DiscordUser newUser(final String id) {
        final DiscordUser user = new DiscordUser();
        user.setId(Long.valueOf(id));
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

    private Elo newElo(final Integer value, final Driver driver, final Game game) {
        final Elo elo = new Elo(driver, game);
        elo.setValue(value);
        elo.setDriver(driver);
        elo.setGame(game);
        return elo;
    }

    private Event newEvent(final String name, final Date startDate, final League league) {
        final Event event = new Event();
        event.setName(name);
        event.setStartDate(startDate);
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
        return raceResult;
    }

    private Split newSplit(final String leagueSplit, final League league) {
        final Split split = new Split();
        split.setSplit(leagueSplit);
        split.setLeague(league);
        return split;
    }
}
