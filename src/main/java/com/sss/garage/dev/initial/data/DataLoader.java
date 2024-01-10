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
        setupF1GPPoints(); // also other AC races
        setupF2Feature2019Points(); // also AC F3
        setupF2Feature2022Points();
        setupF2Sprint2019Points(); // also AC F3
        setupF2Sprint2022Points();
        setupACCPoints(); // also AC GT2
        setupACIMSAPoints();
        setupACDTMRacePoints();
        setupACWTCRRacePoints();
        setupACWTCRQualiPoints();
        setupAC2020QualiPoints();
        setupACF4Race2Points();
        setupACPragaR1Race2Points();
        setupACATCCRace1Points();
        setupACATCCRace2Points();
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
        final RacePointDictionary f1GPPoints = new RacePointDictionary(RacePointType.F1_GP, points, true, 1, true, false, 0);
        racePointDictionaryRepository.save(f1GPPoints);
    }

    private void setupF2Feature2019Points() {
        if(racePointDictionaryRepository.existsByRacePointType(RacePointType.F2_FEATURE_2019)) {
            return;
        }
        List<Integer> points = List.of(25, 18, 15, 12, 10, 8, 6, 4, 2, 1);
        final RacePointDictionary f2Feature2019Points = new RacePointDictionary(RacePointType.F2_FEATURE_2019, points, true, 2, true, true, 4);
        racePointDictionaryRepository.save(f2Feature2019Points);
    }

    private void setupF2Feature2022Points() {
        if(racePointDictionaryRepository.existsByRacePointType(RacePointType.F2_FEATURE_2022)) {
            return;
        }
        List<Integer> points = List.of(25, 18, 15, 12, 10, 8, 6, 4, 2, 1);
        final RacePointDictionary f2Feature2022Points = new RacePointDictionary(RacePointType.F2_FEATURE_2022, points, true, 1, true, true, 2);
        racePointDictionaryRepository.save(f2Feature2022Points);
    }

    private void setupF2Sprint2019Points() {
        if(racePointDictionaryRepository.existsByRacePointType(RacePointType.F2_SPRINT_2019)) {
            return;
        }
        List<Integer> points = List.of(15, 12, 10, 8, 6, 4, 2, 1);
        final RacePointDictionary f2Sprint2019Points = new RacePointDictionary(RacePointType.F2_SPRINT_2019, points, true, 2, true, true, 4);
        racePointDictionaryRepository.save(f2Sprint2019Points);
    }

    private void setupF2Sprint2022Points() {
        if(racePointDictionaryRepository.existsByRacePointType(RacePointType.F2_SPRINT_2022)) {
            return;
        }
        List<Integer> points = List.of(10, 8, 6, 5, 4, 3, 2, 1);
        final RacePointDictionary f2Sprint2022Points = new RacePointDictionary(RacePointType.F2_SPRINT_2022, points, true, 1, true, true, 2);
        racePointDictionaryRepository.save(f2Sprint2022Points);
    }

    private void setupACCPoints() {
        if(racePointDictionaryRepository.existsByRacePointType(RacePointType.ACC)) {
            return;
        }
        List<Integer> points = List.of(32, 27, 23, 20, 18, 16, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
        final RacePointDictionary ACCPoints = new RacePointDictionary(RacePointType.ACC, points, true, 3, false, true, 3);
        racePointDictionaryRepository.save(ACCPoints);
    }

    private void setupACIMSAPoints() {
        if(racePointDictionaryRepository.existsByRacePointType(RacePointType.AC_IMSA)) {
            return;
        }
        List<Integer> points = List.of(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
        final RacePointDictionary ACIMSAPoints = new RacePointDictionary(RacePointType.AC_IMSA, points, false, 0, false, false, 0);
        racePointDictionaryRepository.save(ACIMSAPoints);
    }

    private void setupACWTCRRacePoints() {
        if(racePointDictionaryRepository.existsByRacePointType(RacePointType.AC_WTCR_RACE)) {
            return;
        }
        List<Integer> points = List.of(25, 20, 16, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
        final RacePointDictionary ACWTCRRacePoints = new RacePointDictionary(RacePointType.AC_WTCR_RACE, points, false, 0, false, false, 0);
        racePointDictionaryRepository.save(ACWTCRRacePoints);
    }

    private void setupACDTMRacePoints() {
        if(racePointDictionaryRepository.existsByRacePointType(RacePointType.AC_DTM)) {
            return;
        }
        List<Integer> points = List.of(23, 20, 17, 14, 12, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
        final RacePointDictionary ACWTCRRacePoints = new RacePointDictionary(RacePointType.AC_DTM, points, true, 2, false, false, 0);
        racePointDictionaryRepository.save(ACWTCRRacePoints);
    }

    private void setupACWTCRQualiPoints() {
        if(racePointDictionaryRepository.existsByRacePointType(RacePointType.AC_WTCR_QUALI)) {
            return;
        }
        List<Integer> points = List.of(5, 4, 3, 2, 1);
        final RacePointDictionary ACWTCRQualiPoints = new RacePointDictionary(RacePointType.AC_WTCR_QUALI, points, false, 0, false, false, 0);
        racePointDictionaryRepository.save(ACWTCRQualiPoints);
    }

    private void setupAC2020QualiPoints() {
        if(racePointDictionaryRepository.existsByRacePointType(RacePointType.AC_2020_QUALI)) {
            return;
        }
        List<Integer> points = List.of(3, 2, 1);
        final RacePointDictionary AC2020QualiPoints = new RacePointDictionary(RacePointType.AC_2020_QUALI, points, false, 0, false, false, 0);
        racePointDictionaryRepository.save(AC2020QualiPoints);
    }

    private void setupACF4Race2Points() {
        if(racePointDictionaryRepository.existsByRacePointType(RacePointType.AC_F4_RACE_2)) {
            return;
        }
        List<Integer> points = List.of(12, 9, 7, 6, 5, 4, 3, 2, 1);
        final RacePointDictionary ACF4Race2Points = new RacePointDictionary(RacePointType.AC_F4_RACE_2, points, false, 0, false, false, 0);
        racePointDictionaryRepository.save(ACF4Race2Points);
    }

    private void setupACPragaR1Race2Points() {
        if(racePointDictionaryRepository.existsByRacePointType(RacePointType.AC_PRAGA_R1_RACE_2)) {
            return;
        }
        List<Integer> points = List.of(16, 13, 11, 10, 9, 8, 7, 6, 6, 5, 5, 4, 4, 3, 3, 2, 2, 1, 1);
        final RacePointDictionary ACPragaR1Race2Points = new RacePointDictionary(RacePointType.AC_PRAGA_R1_RACE_2, points, true, 1, false, true, 1);
        racePointDictionaryRepository.save(ACPragaR1Race2Points);
    }

    private void setupACATCCRace1Points() {
        if(racePointDictionaryRepository.existsByRacePointType(RacePointType.AC_ATCC_RACE_1)) {
            return;
        }
        List<Integer> points = List.of(10, 8, 6, 4, 3, 2, 1);
        final RacePointDictionary ACATCCRace1Points = new RacePointDictionary(RacePointType.AC_ATCC_RACE_1, points, false, 0, false, false, 0);
        racePointDictionaryRepository.save(ACATCCRace1Points);
    }

    private void setupACATCCRace2Points() {
        if(racePointDictionaryRepository.existsByRacePointType(RacePointType.AC_ATCC_RACE_2)) {
            return;
        }
        List<Integer> points = List.of(14, 12, 10, 8, 6, 4, 2);
        final RacePointDictionary ACATCCRace2Points = new RacePointDictionary(RacePointType.AC_ATCC_RACE_2, points, false, 0, false, false, 0);
        racePointDictionaryRepository.save(ACATCCRace2Points);
    }
}
