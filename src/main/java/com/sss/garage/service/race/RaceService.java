package com.sss.garage.service.race;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.sss.garage.model.race.Race;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RaceService {
    Optional<Race> findById(final Long id);
    List<Race> getAllScoringRacesSorted();
    List<Race> getGameSortedRacesSince(final Race race);
    List<Race> getAllScoringRacesNotIncludedInEloSorted();
    Boolean isQuali(final Race race);
    void save(final Race race);
    void saveAll(final Collection<Race> races);
    Page<Race> getAllPlayableRaces(final Pageable pageable);
    Page<Race> getCompletedPlayableRaces(final Pageable pageable);
    Page<Race> getUncompletedPlayableRaces(final Pageable pageable);
}
