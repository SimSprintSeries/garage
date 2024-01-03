package com.sss.garage.dev.initial.data.legacy;

import static com.sss.garage.constants.WebConstants.PARENT_RACE_NAME;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sss.garage.dev.initial.data.legacy.converter.LegacyDriverConverter;
import com.sss.garage.dev.initial.data.legacy.model.*;
import com.sss.garage.model.acclap.AccLap;
import com.sss.garage.model.acclap.AccLapRepository;
import com.sss.garage.model.cartable.CarTable;
import com.sss.garage.model.cartable.CarTableRepository;
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
import com.sss.garage.model.racepointdictionary.RacePointDictionaryRepository;
import com.sss.garage.model.racepointtype.RacePointType;
import com.sss.garage.model.raceresult.RaceResult;
import com.sss.garage.model.raceresult.RaceResultRepository;
import com.sss.garage.model.split.Split;
import com.sss.garage.model.split.SplitRepository;
import com.sss.garage.model.track.Track;
import com.sss.garage.model.track.TrackRepository;
import com.sss.garage.model.user.DiscordUser;
import com.sss.garage.model.user.DiscordUserRepository;

import org.hibernate.Hibernate;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

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

    @Value("${legacy.data.dir}/testsession.json")
    private Resource accSessionResource;

    @Value("${legacy.data.dir}/cartable.json")
    private Resource carsResource;

    @Value("${legacy.data.dir}/tracks.json")
    private Resource tracksResource;

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

    private CarTableRepository carTableRepository;

    private TrackRepository trackRepository;

    private RacePointDictionaryRepository racePointDictionaryRepository;

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
                    //raceResult.setPointsForPosition(findPointsForPosition(raceResult));
                    return raceResult;
                })
                .collect(Collectors.toSet());
        raceResultRepository.saveAll(raceResults);

        String lapsJsonObject = convertJsonString(objectMapper.readValue(accSessionResource.getFile(), JSONObject.class).get("laps").toString());

        String resultsJsonObject = convertJsonString(objectMapper.readValue(accSessionResource.getFile(), JSONObject.class).get("sessionResult").toString());

        String leaderBoardLinesJsonObject = convertJsonString(objectMapper.readValue(resultsJsonObject, JSONObject.class).get("leaderBoardLines").toString());

        JSONObject[] driverJsonArrayOriginal = objectMapper.readValue(leaderBoardLinesJsonObject, JSONObject[].class);

        List<JSONObject> driverJsonArrayList = new ArrayList<>(Arrays.asList(driverJsonArrayOriginal));

        for (JSONObject jsonObject : driverJsonArrayOriginal) {
            JSONObject carObject = objectMapper.readValue(convertJsonString(jsonObject.get("car").toString()), JSONObject.class);
            JSONObject[] driverArray = objectMapper.readValue(convertJsonString(carObject.get("drivers").toString()), JSONObject[].class);
            jsonObject.keySet().removeIf(k -> !k.equals("a"));
            for (int i=0;i<driverArray.length;i++) {
                JSONObject newJsonObject = new JSONObject();
                newJsonObject.put("carId", carObject.get("carId"));
                newJsonObject.put("raceNumber", carObject.get("raceNumber"));
                newJsonObject.put("carModel", carObject.get("carModel"));
                newJsonObject.put("driverIndex", i);
                newJsonObject.put("firstName", driverArray[i].get("firstName"));
                newJsonObject.put("lastName", driverArray[i].get("lastName"));
                newJsonObject.put("shortName", driverArray[i].get("shortName"));
                driverJsonArrayList.add(newJsonObject);
            }
        }

        JSONObject[] driverJsonArray = new JSONObject[driverJsonArrayList.size()];
        driverJsonArray = driverJsonArrayList.toArray(driverJsonArray);

        JsonNode root = objectMapper.readTree(lapsJsonObject);

        root.forEach(
                card -> {
                    ObjectNode cardObject = (ObjectNode) card;
                    ArrayNode splitsNode = (ArrayNode) cardObject.remove("splits");
                    for(int i=0; i < splitsNode.size(); i++) {
                        cardObject.put("sector"+(i+1), splitsNode.get(i));
                    }
                }
        );

        String lapsJsonString = objectMapper.writeValueAsString(root);

        JSONObject[] lapsJsonArray = objectMapper.readValue(lapsJsonString, JSONObject[].class);

        for (int i=0;i<lapsJsonArray.length;i++) {
            for (int j=0;j<driverJsonArray.length;j++) {
                if (lapsJsonArray[i].get("carId").equals(driverJsonArray[j].get("carId")) && lapsJsonArray[i].get("driverIndex").equals(driverJsonArray[j].get("driverIndex"))) {
                    lapsJsonArray[i].put("firstName", driverJsonArray[j].get("firstName"));
                    lapsJsonArray[i].put("lastName", driverJsonArray[j].get("lastName"));
                    lapsJsonArray[i].put("shortName", driverJsonArray[j].get("shortName"));
                    lapsJsonArray[i].put("raceNumber", driverJsonArray[j].get("raceNumber"));
                    lapsJsonArray[i].put("carModel", driverJsonArray[j].get("carModel"));
                    lapsJsonArray[i].put("trackName", objectMapper.readValue(accSessionResource.getFile(), JSONObject.class).get("trackName"));
                    lapsJsonArray[i].put("sessionType", objectMapper.readValue(accSessionResource.getFile(), JSONObject.class).get("sessionType"));
                    lapsJsonArray[i].put("serverName", objectMapper.readValue(accSessionResource.getFile(), JSONObject.class).get("serverName").toString().split("-")[1].strip());
                }
            }
        }

        List<LegacyAccLap> legacyAccLaps = Arrays.asList(objectMapper.readValue(Arrays.toString(lapsJsonArray), LegacyAccLap[].class));
        Set<AccLap> accLaps = legacyAccLaps.stream()
                .map(l -> {
                    final AccLap accLap = new AccLap();
                    accLap.setCarId(l.carId);
                    accLap.setDriverIndex(l.driverIndex);
                    accLap.setLaptime(String.valueOf(((float)l.laptime)/1000));
                    accLap.setIsValidForBest(l.isValidForBest);
                    accLap.setSector1(String.valueOf(((float)l.sector1/1000)));
                    accLap.setSector2(String.valueOf(((float)l.sector2/1000)));
                    accLap.setSector3(String.valueOf(((float)l.sector3/1000)));
                    accLap.setFirstName(l.firstName);
                    accLap.setLastName(l.lastName);
                    accLap.setShortName(l.shortName);
                    accLap.setCarModel(l.carModel);
                    accLap.setRaceNumber(l.raceNumber);
                    accLap.setTrackName(l.trackName);
                    accLap.setSessionType(l.sessionType);
                    accLap.setServerName(l.serverName);
                    return accLap;
                })
                .collect(Collectors.toSet());
        accLapRepository.saveAll(accLaps);

        List<LegacyCarTable> legacyCars = Arrays.asList(objectMapper.readValue(carsResource.getFile(), LegacyCarTable[].class));

        Set<CarTable> cars = legacyCars.stream()
                .map(c -> {
                    final CarTable carTable = new CarTable();
                    carTable.setId(c.id);
                    carTable.setCarModel(c.carModel);
                    return carTable;
                })
                .collect(Collectors.toSet());
        carTableRepository.saveAll(cars);
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

    private RacePointType findRacePointType(final Race race) {
        RacePointType racePointType = RacePointType.F1_GP;

        League league = race.getEvent().getLeague();
        Game game = league.getGame();

        if(game.getName().equals("F2 2019") || game.getName().equals("F2 2020")
                || (game.getName().equals("AC") && league.getName().contains("F3"))) {
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
            if(league.getName().contains("IMSA") && race.getName().contains("Wyścig")) {
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
            }
        }
        return racePointType;
    }

    /*
    private Integer findPointsForPosition(final RaceResult raceResult) {

        System.out.println(raceResult.getDriver().getName() + raceResult.getRace().getName() + raceResult.getFinishPosition());
        System.out.println(racePointDictionaryRepository.findByRacePointType(raceResult.getRace().getPointType()).orElseThrow().pointsForPosition(1));
        return racePointDictionaryRepository.findByRacePointType(raceResult.getRace().getPointType())
                .orElseThrow().pointsForPosition(raceResult.getFinishPosition());
    }
    */

    // Konwersja JSONa - dodanie cudzysłowów do wartości tekstowych w stringu, zmiana z "=" na ":", usunięcie problematycznych i niepotrzebnych pól
    private String convertJsonString(final String oldValue) {
        String updated = oldValue.replaceAll("teamName=, ", "").replaceAll(", playerId=S.................", "")
                .replace("{", " { ").replace("}", " } ").replace("[", " [ ")
                .replace("]", " ] ").replace(",", " , ").replace("=", " = ");

        String[] listed = updated.split(" ");

        StringBuilder sb = new StringBuilder();

        Pattern p = Pattern.compile("[A-Za-z].*[A-Za-z]");

        for(Integer i=0; i<listed.length; i++) {
            Matcher m = p.matcher(listed[i]);
            while (m.find())
            {
                listed[i] = "\"" + listed[i] + "\"";
            }
            m.appendTail(sb);
        }

        return String.join("", listed).replace("=", ":");
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
    public void setCarTableRepository(final CarTableRepository carTableRepository) {
        this.carTableRepository = carTableRepository;
    }

    @Autowired
    public void setTrackRepository(final TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Autowired
    public void setRacePointDictionaryRepository(RacePointDictionaryRepository racePointDictionaryRepository) {
        this.racePointDictionaryRepository = racePointDictionaryRepository;
    }
}
