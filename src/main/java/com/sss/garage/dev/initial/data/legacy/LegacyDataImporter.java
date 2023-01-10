package com.sss.garage.dev.initial.data.legacy;

import static com.sss.garage.constants.WebConstants.PARENT_RACE_NAME;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sss.garage.dev.initial.data.legacy.converter.LegacyDriverConverter;
import com.sss.garage.dev.initial.data.legacy.model.LegacyDriver;
import com.sss.garage.dev.initial.data.legacy.model.LegacyEvent;
import com.sss.garage.dev.initial.data.legacy.model.LegacyLeague;
import com.sss.garage.dev.initial.data.legacy.model.LegacyRace;
import com.sss.garage.dev.initial.data.legacy.model.LegacyRaceResult;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.driver.DriverRepository;
import com.sss.garage.model.event.Event;
import com.sss.garage.model.event.EventRepository;
import com.sss.garage.model.game.Game;
import com.sss.garage.model.game.GameRepository;
import com.sss.garage.model.game.family.GameFamily;
import com.sss.garage.model.game.family.GameFamilyRepository;
import com.sss.garage.model.league.League;
import com.sss.garage.model.league.LeagueRepository;
import com.sss.garage.model.race.Race;
import com.sss.garage.model.race.RaceRepository;
import com.sss.garage.model.raceresult.RaceResult;
import com.sss.garage.model.raceresult.RaceResultRepository;
import com.sss.garage.model.split.Split;
import com.sss.garage.model.split.SplitRepository;
import com.sss.garage.model.user.DiscordUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class LegacyDataImporter {

    @Value("file:${legacy.data.dir}/leagues.json")
    private Resource leaguesResource;

    @Value("file:${legacy.data.dir}/drivers.json")
    private Resource driversResource;

    @Value("file:${legacy.data.dir}/events.json")
    private Resource eventsResource;

    @Value("file:${legacy.data.dir}/raceresults.json")
    private Resource raceResultsResource;

    @Value("file:${legacy.data.dir}/races.json")
    private Resource racesResource;

    private DiscordUserRepository discordUserRepository;

    private DriverRepository driverRepository;

    private GameFamilyRepository gameFamilyRepository;

    private GameRepository gameRepository;

    private LeagueRepository leagueRepository;

    private SplitRepository splitRepository;

    private EventRepository eventRepository;

    private RaceRepository raceRepository;

    private RaceResultRepository raceResultRepository;

    ObjectMapper objectMapper;

    public void importLegacyData() throws IOException {
        Converter<LegacyDriver, Driver> legacyDriverDriverConverter = new LegacyDriverConverter();
        List<LegacyDriver> legacyDrivers = Arrays.asList(objectMapper.readValue(driversResource.getFile(), LegacyDriver[].class));

        Set<Driver> drivers = legacyDrivers.stream()
                .map(legacyDriverDriverConverter::convert)
                .filter(Objects::nonNull)
//                .peek(d -> Optional.ofNullable(d.getDiscordUser()).ifPresent(discordUserRepository::save))
                .collect(Collectors.toSet());

        driverRepository.saveAll(drivers);

        List<LegacyLeague> legacyLeagues = Arrays.asList(objectMapper.readValue(leaguesResource.getFile(), LegacyLeague[].class));

        final GameFamily f1Family = newGameFamily("F1");
        final GameFamily acFamily = newGameFamily("AC");
        final GameFamily otherFamily = newGameFamily("Other");

        gameFamilyRepository.saveAll(Set.of(f1Family, acFamily, otherFamily));

        Set<Game> games = legacyLeagues.stream()
                .map(LegacyLeague::getGame)
                .distinct()
//                .filter(game -> !game.equals("other"))
                .map(g -> {
                    final Game game = new Game();
                    game.setName(g);
                    game.setGameFamily(determineGameFamily(g, f1Family, acFamily, otherFamily));
                    return game;
                })
                .collect(Collectors.toSet());
        gameRepository.saveAll(games);

        Set<League> leagues = legacyLeagues.stream()
                .map(l -> {
                    final League league = new League();
                    league.setName(l.name);
                    league.setPlatform(l.platform);
                    league.setGame(findGameByName(l.game, games));
                    return league;
                })
                .collect(Collectors.toSet());
        leagueRepository.saveAll(leagues);

        Set<Split> splits = legacyLeagues.stream()
                .map(l -> {
                    final Split split = new Split();
                    split.setLeague(findLeagueByName(l.name, leagues));
                    split.setSplit(l.split);
                    return split;
                })
                .collect(Collectors.toSet());
        splitRepository.saveAll(splits);

        List<LegacyEvent> legacyEvents = Arrays.asList(objectMapper.readValue(eventsResource.getFile(), LegacyEvent[].class));

        Set<Event> events = legacyEvents.stream()
                .map(e -> {
                    final Event event = new Event();
                    event.setName(e.name);
                    event.setSprite(e.country);
                    event.setLeague(findLeagueByLegacyId(e.league_id, leagues, legacyLeagues));
                    return event;
                })
                .collect(Collectors.toSet());
        eventRepository.saveAll(events);

        List<LegacyRace> legacyRaces = Arrays.asList(objectMapper.readValue(racesResource.getFile(), LegacyRace[].class));

        Set<Race> races = legacyRaces.stream()
                .map(r -> {
                    final Race race = new Race();
                    race.setName(r.racename);
                    race.setEvent(findEventByLegacyId(r.eventid, events, legacyEvents, leagues, legacyLeagues));
                    race.setSplit(findSplitByLeagueId(race.getEvent().getLeague().getId(), splits, leagues, legacyLeagues));
                    return race;
                })
                .collect(Collectors.toSet());

        Map<Event, List<Race>> multipleRacesRaces = races.stream()
                        .collect(Collectors.groupingBy(Race::getEvent));

        multipleRacesRaces.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                        .forEach(e -> {
                            final Race parentRace = new Race();
                            parentRace.setEvent(e.getKey());
                            parentRace.setName(PARENT_RACE_NAME);
                            parentRace.setSplit(e.getValue().stream().findFirst().get().getSplit());
                            e.getValue().forEach(r -> r.setParentRaceEvent(parentRace));
                            races.add(parentRace);
                        });
        raceRepository.saveAll(races);

        List<LegacyRaceResult> legacyRaceResults = Arrays.asList(objectMapper.readValue(raceResultsResource.getFile(), LegacyRaceResult[].class));
        Set<RaceResult> raceResults = legacyRaceResults.stream()
                .map(r -> {
                    final RaceResult raceResult = new RaceResult();
                    raceResult.setFastestLap(r.lap);
                    raceResult.setDnf(r.dnf);
                    raceResult.setPolePosition(r.pole);
                    raceResult.setDsq(r.dsq);
                    raceResult.setFinishPosition(Math.toIntExact(r.position));
                    raceResult.setRace(findRaceByLegacyId(r.raceid, r.eventid, races, legacyRaces, events, legacyEvents, leagues, legacyLeagues));
                    raceResult.setDriver(findDriverByLegacyId(r.driver, drivers, legacyDrivers));
                    return raceResult;
                })
                .collect(Collectors.toSet());
        raceResultRepository.saveAll(raceResults);
    }

    private static Driver findDriverByLegacyId(final Long id, final Set<Driver> drivers, final List<LegacyDriver> legacyDrivers) {
        final LegacyDriver legacyDriver = legacyDrivers.stream()
                .filter(d -> id.equals(d.id))
                .findFirst().get();

        return drivers.stream()
                .filter(d -> d.getName().equals(legacyDriver.name))
                .findFirst().get();
    }

    private static Game findGameByName(final String name, Set<Game> games) {
        return games.stream()
                .filter(g -> name.equals(g.getName()))
                .findFirst().get();//always exists
    }

    private static League findLeagueByLegacyId(final Long id, final Set<League> leagues, final List<LegacyLeague> legacyLeagues) {
        LegacyLeague legacyLeague = legacyLeagues.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst().get();

        return leagues.stream()
                .filter(l -> l.getName().equals(legacyLeague.name))
                .findFirst().get();
    }

    private static League findLeagueByName(final String name, Set<League> leagues) {
        return leagues.stream()
                .filter(g -> name.equals(g.getName()))
                .findFirst().get();//always exists
    }

    private static Race findRaceByLegacyId(final Long raceId, final Long eventId, final Set<Race> races, final List<LegacyRace> legacyRaces, final Set<Event> events, final List<LegacyEvent> legacyEvents, final Set<League> leagues, final List<LegacyLeague> legacyLeagues) {
        final LegacyRace legacyRace = legacyRaces.stream()
                .filter(e -> e.raceid.equals(raceId))
                .findFirst().get();

        return races.stream()
                .filter(r -> r.getName().equals(legacyRace.racename) &&
                        r.getEvent().equals(findEventByLegacyId(eventId, events, legacyEvents, leagues, legacyLeagues)))
                .findFirst().get();
    }

    private static Split findSplitByLeagueId(final Long id, final Set<Split> splits, final Set<League> leagues, final List<LegacyLeague> legacyLeagues) {
        LegacyLeague legacyLeague = legacyLeagues.stream()
                .filter(l -> l.getName().equals(leagues.stream().filter(l2 -> id.equals(l2.getId())).findFirst().get().getName()))
                .findFirst().get();

        return splits.stream()
                .filter(s -> s.getLeague().getId().equals(id) && legacyLeague.getSplit().equals(s.getSplit()))
                .findFirst().get();
    }

    private static Event findEventByLegacyId(final Long id, final Set<Event> events, final List<LegacyEvent> legacyEvents, final Set<League> leagues, final List<LegacyLeague> legacyLeagues) {
        final LegacyEvent legacyEvent = legacyEvents.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst().get();

        return events.stream()
                .filter(e -> e.getName().equals(legacyEvent.name) && e.getLeague().equals(findLeagueByLegacyId(legacyEvent.league_id, leagues, legacyLeagues)))
                .findFirst().get();
    }

    private GameFamily newGameFamily(final String name) {
        final GameFamily gameFamily = new GameFamily();
        gameFamily.setName(name);
        return gameFamily;
    }

    private GameFamily determineGameFamily(final String gameName, final GameFamily f1, final GameFamily ac, final GameFamily other) {
        if(gameName.toLowerCase().contains("f1") || gameName.toLowerCase().contains("f2"))
            return f1;
        if(gameName.toLowerCase().contains("ac"))
            return ac;

        return other;
    }

    @Autowired
    public void setObjectMapper(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired
    public void setDiscordUserRepository(final DiscordUserRepository discordUserRepository) {
        this.discordUserRepository = discordUserRepository;
    }

    @Autowired
    public void setDriverRepository(final DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Autowired
    public void setGameRepository(final GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Autowired
    public void setLeagueRepository(final LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    @Autowired
    public void setSplitRepository(final SplitRepository splitRepository) {
        this.splitRepository = splitRepository;
    }

    @Autowired
    public void setEventRepository(final EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Autowired
    public void setRaceRepository(final RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Autowired
    public void setRaceResultRepository(final RaceResultRepository raceResultRepository) {
        this.raceResultRepository = raceResultRepository;
    }

    @Autowired
    public void setGameFamilyRepository(final GameFamilyRepository gameFamilyRepository) {
        this.gameFamilyRepository = gameFamilyRepository;
    }
}
