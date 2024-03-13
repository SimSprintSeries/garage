package com.sss.garage.dev.initial.data.legacy;

import static com.sss.garage.constants.WebConstants.PARENT_RACE_NAME;

import java.io.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sss.garage.dev.initial.data.legacy.converter.LegacyDriverConverter;
import com.sss.garage.dev.initial.data.legacy.model.*;
import com.sss.garage.model.acclap.AccLap;
import com.sss.garage.model.acclap.AccLapRepository;
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
import com.sss.garage.model.racepointtype.RacePointType;
import com.sss.garage.model.raceresult.RaceResult;
import com.sss.garage.model.raceresult.RaceResultRepository;
import com.sss.garage.model.split.Split;
import com.sss.garage.model.split.SplitRepository;
import com.sss.garage.model.team.Team;
import com.sss.garage.model.team.TeamRepository;
import com.sss.garage.model.track.Track;
import com.sss.garage.model.track.TrackRepository;
import com.sss.garage.model.user.DiscordUser;
import com.sss.garage.model.user.DiscordUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.json.*;

@Component
public class LegacyDataImporter {

    @Value("${legacy.data.dir}/leagues.json")
    private Resource leaguesResource;

    @Value("${legacy.data.dir}/drivers.json")
    private Resource driversResource;

    @Value("${legacy.data.dir}/events.json")
    private Resource eventsResource;

    @Value("${legacy.data.dir}/raceresults.json")
    private Resource raceResultsResource;

    @Value("${legacy.data.dir}/races.json")
    private Resource racesResource;

    @Value("${legacy.data.dir}/cartable.json")
    private Resource carsResource;

    @Value("${legacy.data.dir}/tracks.json")
    private Resource tracksResource;

    @Value("${legacy.data.dir}/teams.json")
    private Resource teamsResource;

    private DiscordUserRepository discordUserRepository;

    private DriverRepository driverRepository;

    private GameFamilyRepository gameFamilyRepository;

    private GameRepository gameRepository;

    private LeagueRepository leagueRepository;

    private SplitRepository splitRepository;

    private EventRepository eventRepository;

    private RaceRepository raceRepository;

    private RaceResultRepository raceResultRepository;

    private AccLapRepository accLapRepository;

    private TrackRepository trackRepository;

    private TeamRepository teamRepository;

    ObjectMapper objectMapper;

    public void importLegacyData() throws IOException {
        LegacyDriverConverter legacyDriverDriverConverter = new LegacyDriverConverter();
        List<LegacyDriver> legacyDrivers = Arrays.asList(objectMapper.readValue(driversResource.getFile(), LegacyDriver[].class));
        Set<DiscordUser> dcUsers = legacyDrivers.stream()
                .filter(d -> Objects.nonNull(d.discordUserId))
                .map(d -> {
                    final DiscordUser user = new DiscordUser();
                    user.setId(d.discordUserId);
                    return user;
                })
                .collect(Collectors.toSet());

        discordUserRepository.saveAll(dcUsers);

        Set<Driver> drivers = legacyDrivers.stream()
                .map(d -> legacyDriverDriverConverter.convert(d, dcUsers))
                .filter(Objects::nonNull)
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
                    league.setBanner(l.banner);
                    league.setLogo(l.logo);
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

        List<LegacyTrack> legacyTracks = Arrays.asList(objectMapper.readValue(tracksResource.getFile(), LegacyTrack[].class));

        Set<Track> tracks = legacyTracks.stream()
                .map(t -> {
                    final Track track = new Track();
                    track.setId(t.id);
                    track.setName(t.name);
                    track.setCountry(t.country);
                    track.setCity(t.city);
                    trackRepository.save(track);
                    return track;
                })
                .collect(Collectors.toSet());

        List<LegacyEvent> legacyEvents = Arrays.asList(objectMapper.readValue(eventsResource.getFile(), LegacyEvent[].class));

        Set<Event> events = legacyEvents.stream()
                .map(e -> {
                    final Event event = new Event();
                    event.setName(e.name);
                    event.setStartDate(e.starts);
                    event.setSprite(e.country);
                    event.setLeague(findLeagueByLegacyId(e.league_id, leagues, legacyLeagues));
                    event.setTrack(findTrackByLegacyId(e.track_id, tracks, legacyTracks));
                    return event;
                })
                .collect(Collectors.toSet());
        eventRepository.saveAll(events);

        for (League league : leagueRepository.findAll()) {
            try {
                setStartDateAndEventCount(league);
            } catch (NullPointerException e) {
                league.setEventCount(0);
            }
            leagueRepository.save(league);
        }

        List<LegacyRace> legacyRaces = Arrays.asList(objectMapper.readValue(racesResource.getFile(), LegacyRace[].class));

        Set<Race> races = legacyRaces.stream()
                .map(r -> {
                    final Race race = new Race();
                    race.setName(r.racename);
                    race.setEvent(findEventByLegacyId(r.eventid, events, legacyEvents, leagues, legacyLeagues));
                    race.setStartDate(findLegacyEventById(r.eventid, legacyEvents).starts);
                    race.setSplit(findSplitByLeagueId(race.getEvent().getLeague().getId(), splits, leagues, legacyLeagues));
                    race.setPointType(findRacePointType(race));
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
                            parentRace.setPointScoring(false);
                            e.getValue().stream().findAny().ifPresent(r -> {
                                parentRace.setStartDate(r.getStartDate());
                            });
                            e.getValue().forEach(r -> {
                                r.setParentRaceEvent(parentRace);
                                r.setDatePlaceholder(false);
                            });
                            races.add(parentRace);
                        });
        raceRepository.saveAll(races);

        List<LegacyTeam> legacyTeams = Arrays.asList(objectMapper.readValue(teamsResource.getFile(), LegacyTeam[].class));
        Set<Team> teams = legacyTeams.stream()
                .map(t -> {
                    final Team team = new Team();
                    team.setId(t.id);
                    team.setName(t.name);
                    team.setColour(t.colour);
                    teamRepository.save(team);
                    return team;
                })
                .collect(Collectors.toSet());

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
                    raceResult.setTeam(findTeamByLegacyId(r.teamid, teams, legacyTeams));
                    raceResult.setComment(r.comment);
                    raceResultRepository.save(raceResult);
                    return raceResult;
                })
                .collect(Collectors.toSet());

        for(File file : new File(System.getProperty("user.dir") + "\\src\\main\\resources\\accsessions").listFiles()) {
            if(file.getAbsolutePath().contains("entrylist") || file.isDirectory()) {
                continue;
            }

            InputStream fis = new FileInputStream(file.getAbsolutePath());

            JsonObject sessionObject = readObject(fis);

            JsonArray leaderBoardLinesArray = sessionObject.getJsonObject("sessionResult").getJsonArray("leaderBoardLines");

            JsonArrayBuilder driverArrayBuilder = Json.createArrayBuilder();

            for(JsonValue leaderBoardLinesValue : leaderBoardLinesArray) {
                JsonObject carObject = readObject(new StringReader(leaderBoardLinesValue.toString())).getJsonObject("car");
                JsonObjectBuilder driverObjectBuilder = Json.createObjectBuilder();
                int i = 0;
                for(JsonValue driverValue : carObject.getJsonArray("drivers")) {
                    JsonObject driverObject = readObject(new StringReader(driverValue.toString()));
                    driverObjectBuilder.add("carId", carObject.getInt("carId"));
                    driverObjectBuilder.add("raceNumber", carObject.getInt("raceNumber"));
                    driverObjectBuilder.add("carModel", carObject.getInt("carModel"));
                    driverObjectBuilder.add("driverIndex", i);
                    driverObjectBuilder.add("firstName", driverObject.getString("firstName"));
                    driverObjectBuilder.add("lastName", driverObject.getString("lastName"));
                    driverObjectBuilder.add("shortName", driverObject.getString("shortName"));
                    driverObjectBuilder.add("steamId", driverObject.getString("playerId"));
                    driverArrayBuilder.add(driverObjectBuilder);
                    i++;
                }
            }

            JsonArray driverJsonArray = driverArrayBuilder.build();

            JsonArray lapJsonArray = sessionObject.getJsonArray("laps");

            JsonArrayBuilder lapArrayBuilder = Json.createArrayBuilder();

            for(JsonValue lapValue : lapJsonArray) {
                JsonObject lapObject = readObject(new StringReader(lapValue.toString()));
                JsonObjectBuilder lapObjectBuilder = Json.createObjectBuilder();
                for(JsonValue driverValue : driverJsonArray) {
                    JsonObject driverObject = readObject(new StringReader(driverValue.toString()));
                    if(lapObject.getInt("carId") == driverObject.getInt("carId") && lapObject.getInt("driverIndex") == driverObject.getInt("driverIndex")) {
                        lapObjectBuilder.add("firstName", driverObject.getString("firstName"));
                        lapObjectBuilder.add("lastName", driverObject.getString("lastName"));
                        lapObjectBuilder.add("shortName", driverObject.getString("shortName"));
                        lapObjectBuilder.add("steamId", driverObject.getString("steamId"));
                        lapObjectBuilder.add("laptime", lapObject.getInt("laptime"));
                        lapObjectBuilder.add("isValidForBest", lapObject.getBoolean("isValidForBest"));
                        lapObjectBuilder.add("raceNumber", driverObject.getInt("raceNumber"));
                        lapObjectBuilder.add("carModel", driverObject.getInt("carModel"));
                        lapObjectBuilder.add("trackName", sessionObject.getString("trackName"));
                        lapObjectBuilder.add("sessionType", sessionObject.getString("sessionType"));
                        lapObjectBuilder.add("serverName", sessionObject.getString("serverName").split("-")[1].strip());
                        for(int i=0;i<lapObject.getJsonArray("splits").size();i++) {
                            lapObjectBuilder.add("sector" + (i+1), lapObject.getJsonArray("splits").getInt(i));
                        }
                        lapArrayBuilder.add(lapObjectBuilder);
                    }
                }
            }

            JsonArray legacyLapJsonArray = lapArrayBuilder.build();

            List<LegacyAccLap> legacyAccLaps = Arrays.asList(objectMapper.readValue(legacyLapJsonArray.toString(), LegacyAccLap[].class));
            List<LegacyCarTable> legacyCarTables = Arrays.asList(objectMapper.readValue(carsResource.getFile(), LegacyCarTable[].class));
            Set<AccLap> accLaps = legacyAccLaps.stream()
                    .map(l -> {
                        final AccLap accLap = new AccLap();
                        accLap.setIsValidForBest(l.isValidForBest);
                        accLap.setSector1(String.valueOf(((float)l.sector1/1000)));
                        accLap.setSector2(String.valueOf(((float)l.sector2/1000)));
                        accLap.setSector3(String.valueOf(((float)l.sector3/1000)));
                        accLap.setLaptime(String.valueOf(((float)l.laptime)/1000));
                        accLap.setFirstName(l.firstName);
                        accLap.setLastName(l.lastName);
                        accLap.setShortName(l.shortName);
                        accLap.setSteamId(l.steamId);
                        accLap.setCarModel(l.carModel);
                        accLap.setCarName(findCarNameByCarModel(l.carModel, legacyCarTables));
                        accLap.setRaceNumber(l.raceNumber);
                        accLap.setTrackName(l.trackName);
                        accLap.setSessionType(l.sessionType);
                        accLap.setServerName(l.serverName);
                        return accLap;
                    })
                    .collect(Collectors.toSet());
            accLapRepository.saveAll(accLaps);
        }
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
                        (r.getEvent() != null ? r.getEvent().equals(findEventByLegacyId(eventId, events, legacyEvents, leagues, legacyLeagues)) :
                        r.getParentRaceEvent().getEvent().equals(findEventByLegacyId(eventId, events, legacyEvents, leagues, legacyLeagues))))
                .findFirst().get();
    }

    private static Team findTeamByLegacyId(final Long id, final Set<Team> teams, final List<LegacyTeam> legacyTeams) {
        LegacyTeam legacyTeam = legacyTeams.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst().get();

        return teams.stream()
                .filter(t -> t.getName().equals(legacyTeam.name))
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

    private static LegacyEvent findLegacyEventById(final Long id, final List<LegacyEvent> legacyEvents) {
        return legacyEvents.stream()
                .filter(g -> id.equals(g.getId()))
                .findFirst().get();//always exists
    }

    private void setStartDateAndEventCount(final League league) {
        LocalDate date = eventRepository.findFirstByLeagueOrderByStartDateAsc(league).getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        league.setStartDate(date.format(formatter));
        league.setEventCount(eventRepository.countByLeague(league));
    }

    private static Track findTrackByLegacyId(final Long id, final Set<Track> tracks, final List<LegacyTrack> legacyTracks) {
        LegacyTrack legacyTrack = legacyTracks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst().get();

        return tracks.stream()
                .filter(l -> l.getName().equals(legacyTrack.name))
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

    private JsonObject readObject(final InputStream inputStream) {
        JsonReader reader = Json.createReader(inputStream);
        JsonObject object = reader.readObject();
        reader.close();
        return object;
    }

    private JsonObject readObject(final StringReader stringReader) {
        JsonReader reader = Json.createReader(stringReader);
        JsonObject object = reader.readObject();
        reader.close();
        return object;
    }

    private static String findCarNameByCarModel(final Integer id, final List<LegacyCarTable> legacyCarTables) {
        LegacyCarTable legacyCarTable = legacyCarTables.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst().get();

        return legacyCarTable.getCarModel();
    }

    private RacePointType findRacePointType(final Race race) {
        RacePointType racePointType = RacePointType.F1_GP;

        League league = race.getEvent().getLeague();
        Game game = league.getGame();

        if(game.getName().equals("F2 2019") || game.getName().equals("F2 2020")) {
            if(race.getName().equals("Sprint")) {
                racePointType = RacePointType.F2_SPRINT_2019;
            } else {
                racePointType = RacePointType.F2_FEATURE_2019;
            }
        } else if (game.getName().equals("F2 22") || game.getName().equals("F2 23")) {
            if(race.getName().equals("Sprint")) {
                racePointType = RacePointType.F2_SPRINT_2022;
            } else {
                racePointType = RacePointType.F2_FEATURE_2022;
            }
        } else if (game.getName().equals("ACC") || league.getName().contains("GT2")) {
            racePointType = RacePointType.ACC;
        } else if (game.getName().equals("AC")) {
            if(league.getName().contains("DTM '90") && race.getName().contains("Wyścig")) {
                racePointType = RacePointType.AC_DTM;
            } else if(league.getName().contains("IMSA") && race.getName().contains("Wyścig")) {
                racePointType = RacePointType.AC_IMSA;
            } else if (league.getName().equals("Assetto Corsa - Wiosna 2020")) {
                if(race.getName().equals("Kwalifikacje")) {
                    racePointType = RacePointType.AC_2020_QUALI;
                }
            } else if (league.getName().contains("WTCR")) {
                if(race.getName().equals("Kwalifikacje")) {
                    racePointType = RacePointType.AC_WTCR_QUALI;
                } else {
                    racePointType = RacePointType.AC_WTCR_RACE;
                }
            } else if (league.getName().contains("Praga")) {
                if(race.getName().equals("Wyścig 1")) {
                    racePointType = RacePointType.ACC;
                } else {
                    racePointType = RacePointType.AC_PRAGA_R1_RACE_2;
                }
            } else if (league.getName().contains("F4")) {
                if(race.getName().equals("Wyścig 2")) {
                    racePointType = RacePointType.AC_F4_RACE_2;
                }
            } else if(league.getName().contains("Australian")) {
                if(race.getName().equals("Wyścig 1")) {
                    racePointType = RacePointType.AC_ATCC_RACE_1;
                } else {
                    racePointType = RacePointType.AC_ATCC_RACE_2;
                }
            } else if (league.getName().contains("Formula 3")) {
                if(race.getName().equals("Wyścig 1")) {
                    racePointType = RacePointType.F2_FEATURE_2019;
                } else {
                    racePointType = RacePointType.F2_SPRINT_2019;
                }
            }
        }
        return racePointType;
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

    @Autowired
    public void setAccLapRepository(final AccLapRepository accLapRepository) {
        this.accLapRepository = accLapRepository;
    }

    @Autowired
    public void setTrackRepository(final TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Autowired
    public void setTeamRepository(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
}
