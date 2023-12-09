package com.sss.garage.model.race;

import com.sss.garage.model.event.Event;
import com.sss.garage.model.league.League;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {
    List<Race> findAllByPointScoring(final Boolean pointScoring, final Sort sort);
    List<Race> findAllByPointScoringAndIncludedInElo(final Boolean pointScoring, final Boolean includedInElo, final Sort sort);
    List<Race> findAllByStartDateGreaterThanEqual(final Date date, final Sort sort);
    @Query("SELECT r FROM Race r LEFT JOIN Event e ON r.event = e " +
            "WHERE :datePlaceholder = true " +
            "AND e.league = :league OR :league IS NULL")
    Page<Race> findAllByDatePlaceholderAndLeague(final Boolean datePlaceholder, final League league, final Pageable pageable);
    @Query("SELECT r FROM Race r LEFT JOIN Event e ON r.event = e " +
            "WHERE :datePlaceholder = true " +
            "AND r.startDate <= :date " +
            "AND e.league = :league OR :league IS NULL")
    Page<Race> findAllByDatePlaceholderAndStartDateGreaterThanAndLeague(final Boolean datePlaceholder, final Date date, final League league, final Pageable pageable);
    @Query("SELECT r FROM Race r LEFT JOIN Event e ON r.event = e " +
            "WHERE :datePlaceholder = true " +
            "AND r.startDate > :date " +
            "AND e.league = :league OR :league IS NULL")
    Page<Race> findAllByDatePlaceholderAndStartDateLessThanEqualAndLeague(final Boolean datePlaceholder, final Date date, final League league, final Pageable pageable);
    @Query("SELECT r FROM Race r WHERE r.id IN (SELECT cr.id FROM Event e LEFT JOIN Race r ON e = r.event " +
            "LEFT JOIN Race cr ON r = cr.parentRaceEvent " + //contained races
            "WHERE r.event = :event OR :event IS NULL) " +
            "OR r.id IN (SELECT r.id FROM Event e LEFT JOIN Race r ON e = r.event " +
            "WHERE r.event = :event OR :event IS NULL " +
            "AND r.parentRaceEvent IS NULL " +
            "AND r.name NOT LIKE '%Parent%')")
    Page<Race> findAllByEvent(final Event event, final Pageable pageable);
}
