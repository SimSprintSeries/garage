package com.sss.garage.service.race;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.sss.garage.model.event.Event;
import com.sss.garage.model.league.League;
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
    Page<Race> getAllPlayableRaces(final League league, final Pageable pageable);
    Page<Race> getCompletedPlayableRaces(final League league, final Pageable pageable);
    Page<Race> getUncompletedPlayableRaces(final League league, final Pageable pageable);
    List<Race> getAllRaces();
    void deleteRace(final Long id);
    Page<Race> getAllRacesByEvent(final Event event, final Pageable pageable);
}
