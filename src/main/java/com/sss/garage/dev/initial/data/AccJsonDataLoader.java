package com.sss.garage.dev.initial.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sss.garage.dev.initial.data.legacy.model.LegacyAccLap;
import com.sss.garage.dev.initial.data.legacy.model.LegacyCarTable;
import com.sss.garage.model.acclap.AccLap;
import com.sss.garage.model.acclap.AccLapRepository;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.driver.DriverRepository;
import com.sss.garage.model.league.League;
import com.sss.garage.model.league.LeagueRepository;
import com.sss.garage.model.track.Track;
import com.sss.garage.model.track.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.json.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class AccJsonDataLoader {

    @Value("${legacy.data.dir}/cartable.json")
    private ClassPathResource carsResource;

    private AccLapRepository lapRepository;

    private TrackRepository trackRepository;

    private DriverRepository driverRepository;

    private LeagueRepository leagueRepository;

    private ObjectMapper objectMapper;

    private final List<File> importedFiles = new ArrayList<>();

    @Scheduled(cron = "0 */5 * ? * *")
    public void loadAccJsonData() throws IOException {
        if(importedFiles.isEmpty() && !lapRepository.findAllByParams(null, null, null).isEmpty()) {
            lapRepository.deleteAll();
        }
        for (File file : new File("/home/debian/garage/src/main/resources/accsessions").listFiles()) { // TODO: ścieżka się rozpierdoli jak coś zmienimy
            if (file.getAbsolutePath().contains("entrylist") || file.isDirectory() || importedFiles.contains(file)) {
                continue;
            }

            InputStream fis = new FileInputStream(file.getAbsolutePath());

            JsonObject sessionObject;

            try {
                sessionObject = readObject(fis);
            } catch (JsonException e) {
                continue;
            }

            JsonArray leaderBoardLinesArray = sessionObject.getJsonObject("sessionResult").getJsonArray("leaderBoardLines");

            JsonArrayBuilder driverArrayBuilder = Json.createArrayBuilder();

            for (JsonValue leaderBoardLinesValue : leaderBoardLinesArray) {
                JsonObject carObject = readObject(new StringReader(leaderBoardLinesValue.toString())).getJsonObject("car");
                JsonObject timingObject = readObject(new StringReader(leaderBoardLinesValue.toString())).getJsonObject("timing");
                JsonObjectBuilder driverObjectBuilder = Json.createObjectBuilder();
                int i = 0;
                for (JsonValue driverValue : carObject.getJsonArray("drivers")) {
                    JsonObject driverObject = readObject(new StringReader(driverValue.toString()));
                    driverObjectBuilder.add("carId", carObject.getInt("carId"));
                    driverObjectBuilder.add("raceNumber", carObject.getInt("raceNumber"));
                    driverObjectBuilder.add("carModel", carObject.getInt("carModel"));
                    driverObjectBuilder.add("driverIndex", i);
                    driverObjectBuilder.add("firstName", driverObject.getString("firstName"));
                    driverObjectBuilder.add("lastName", driverObject.getString("lastName"));
                    driverObjectBuilder.add("shortName", driverObject.getString("shortName"));
                    driverObjectBuilder.add("steamId", driverObject.getString("playerId"));
                    driverObjectBuilder.add("totalTime", timingObject.getInt("totalTime"));
                    driverObjectBuilder.add("totalLaps", timingObject.getInt("lapCount"));
                    driverArrayBuilder.add(driverObjectBuilder);
                    i++;
                }
            }

            JsonArray driverJsonArray = driverArrayBuilder.build();

            JsonArray lapJsonArray = sessionObject.getJsonArray("laps");

            JsonArrayBuilder lapArrayBuilder = Json.createArrayBuilder();

            for (JsonValue lapValue : lapJsonArray) {
                JsonObject lapObject = readObject(new StringReader(lapValue.toString()));
                JsonObjectBuilder lapObjectBuilder = Json.createObjectBuilder();
                for (JsonValue driverValue : driverJsonArray) {
                    JsonObject driverObject = readObject(new StringReader(driverValue.toString()));
                    if (lapObject.getInt("carId") == driverObject.getInt("carId") && lapObject.getInt("driverIndex") == driverObject.getInt("driverIndex")) {
                        if (lapObject.getJsonArray("splits").size() !=3) {
                            continue;
                        }
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
                        lapObjectBuilder.add("metaData", sessionObject.getString("metaData"));
                        for (int i = 0; i < lapObject.getJsonArray("splits").size(); i++) {
                            lapObjectBuilder.add("sector" + (i + 1), lapObject.getJsonArray("splits").getInt(i));
                        }
                        lapObjectBuilder.add("totalTime", driverObject.getInt("totalTime"));
                        lapObjectBuilder.add("totalLaps", driverObject.getInt("totalLaps"));
                        lapArrayBuilder.add(lapObjectBuilder);
                    }
                }
            }

            JsonArray legacyLapJsonArray = lapArrayBuilder.build();

            List<LegacyAccLap> legacyAccLaps = Arrays.asList(objectMapper.readValue(legacyLapJsonArray.toString(), LegacyAccLap[].class));
            List<LegacyCarTable> legacyCarTables = Arrays.asList(objectMapper.readValue(carsResource.getInputStream(), LegacyCarTable[].class));
            Set<AccLap> accLaps = legacyAccLaps.stream()
                    .map(l -> {
                        final AccLap accLap = new AccLap();
                        accLap.setIsValidForBest(l.isValidForBest);
                        accLap.setSector1(String.valueOf(((float) l.sector1 / 1000)));
                        accLap.setSector2(String.valueOf(((float) l.sector2 / 1000)));
                        accLap.setSector3(String.valueOf(((float) l.sector3 / 1000)));
                        accLap.setLaptime(String.valueOf(((float) l.laptime) / 1000));
                        accLap.setFirstName(l.firstName);
                        accLap.setLastName(l.lastName);
                        accLap.setShortName(l.shortName);
                        accLap.setSteamId(l.steamId);
                        accLap.setDriver(findDriverBySteamId(accLap));
                        accLap.setCarModel(l.carModel);
                        accLap.setCarName(findCarNameByCarModel(l.carModel, legacyCarTables));
                        accLap.setRaceNumber(l.raceNumber);
                        accLap.setTrack(findTrackByName(l.trackName));
                        accLap.setSessionType(l.sessionType);
                        accLap.setMetaData(l.metaData);
                        accLap.setLeague(findLeagueByServerChampionshipId(accLap));
                        accLap.setTotalTime(String.valueOf(((float) l.totalTime) / 1000));
                        accLap.setTotalLaps(l.totalLaps);
                        return accLap;
                    })
                    .collect(Collectors.toSet());
            lapRepository.saveAll(accLaps);
            importedFiles.add(file);
        }
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

    private Driver findDriverBySteamId(final AccLap lap) {
        try {
            return driverRepository.findBySteamId(Long.valueOf(lap.getSteamId().substring(1)));
        } catch (NullPointerException e) {
            return null;
        }
    }

    private League findLeagueByServerChampionshipId(final AccLap lap) {
        try {
            return leagueRepository.findByAccServerChampionshipId(lap.getMetaData().split(":")[1]);
        } catch (NullPointerException e) {
            return null;
        }
    }

    private Track findTrackByName(final String trackName) {
        return trackRepository.findByAccName(trackName);
    }

    @Autowired
    public void setLapRepository(final AccLapRepository lapRepository) {
        this.lapRepository = lapRepository;
    }

    @Autowired
    public void setTrackRepository(final TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Autowired
    public void setDriverRepository(final DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Autowired
    public void setLeagueRepository(final LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    @Autowired
    public void setObjectMapper(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
