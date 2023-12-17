package com.sss.garage.dev.initial.data;

import java.io.IOException;
import java.util.List;

import com.sss.garage.dev.initial.data.legacy.LegacyDataImporter;
import com.sss.garage.model.race.RaceRepository;
import com.sss.garage.model.racepointdictionary.RacePointDictionary;
import com.sss.garage.model.racepointdictionary.RacePointDictionaryRepository;
import com.sss.garage.model.racepointtype.RacePointType;
import com.sss.garage.service.discord.api.DiscordApiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataLoader {

    private final DiscordApiService discordApiService;

    private final LegacyDataImporter legacyDataImporter;

    private final RaceRepository raceRepository;

    private final RacePointDictionaryRepository racePointDictionaryRepository;

    @Autowired
    public DataLoader(final DiscordApiService discordApiService,
                      final LegacyDataImporter legacyDataImporter,
                      final RaceRepository raceRepository, final RacePointDictionaryRepository racePointDictionaryRepository) {
        this.discordApiService = discordApiService;
        this.legacyDataImporter = legacyDataImporter;
        this.raceRepository = raceRepository;
        this.racePointDictionaryRepository = racePointDictionaryRepository;
        loadInitialData();
    }

    public void loadInitialData() {
        discordApiService.persistAllRoles();

        setupPointsDictionaries();

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

    private void setupPointsDictionaries() {
        setupF1GPPoints();
    }

    private void setupF1GPPoints() {
//        final Race race = raceRepository.findById(486L).orElseThrow();
//        race.setPointType(RacePointType.F1_GP);
//        raceRepository.save(race);
//
//        racePointDictionaryRepository.findByRacePointType(race.getPointType()).orElseThrow().pointsForPosition(1);

        if(racePointDictionaryRepository.existsByRacePointType(RacePointType.F1_GP)) {
            return;
        }
        List<Integer> points = List.of(25, 18, 15, 12, 10, 8, 6, 4, 2, 1);
        final RacePointDictionary f1GPPoints = new RacePointDictionary(RacePointType.F1_GP, points, true, 1);
        racePointDictionaryRepository.save(f1GPPoints);
    }

}
