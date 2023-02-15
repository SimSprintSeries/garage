package com.sss.garage.service.race;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.sss.garage.model.race.Race;

public interface RaceService {
    Optional<Race> findById(final Long id);
    List<Race> getAllRacesSorted();
    List<Race> getGameSortedRacesSince(final Race race);
    List<Race> getAllRacesNotIncludedInEloSorted();
    Boolean isQuali(final Race race);
    void save(final Race race);
    void saveAll(final Collection<Race> races);
}
