package com.sss.garage.model.event;

import com.sss.garage.model.league.League;
import com.sss.garage.model.track.Track;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Integer countByLeague(League league);

    Event findFirstByLeagueOrderByStartDateAsc(League league);

    @Query("SELECT e FROM Event e WHERE (e.league = :league OR :league IS NULL ) " +
            "AND (e.track=:track OR :track IS NULL)")
    Page<Event> findAllByParams(final League league, final Track track, final Pageable pageable);

    @Query("SELECT e FROM Event e WHERE e.league = :league " +
            "AND e.startDate > NOW() " +
            "ORDER BY e.startDate ASC LIMIT 1")
    Event findNextEventByLeague(League league);
}
