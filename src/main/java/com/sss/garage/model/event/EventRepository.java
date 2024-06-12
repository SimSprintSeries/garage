package com.sss.garage.model.event;

import com.sss.garage.model.game.family.GameFamily;
import com.sss.garage.model.game.family.GameFamilyRepository;
import com.sss.garage.model.league.League;
import com.sss.garage.model.track.Track;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Integer countByLeague(League league);

    @Query("SELECT e FROM Event e " +
            "JOIN Race r on r.event = e " +
            "WHERE (e.league = :league OR :league IS NULL ) " +
            "AND (e.track=:track OR :track IS NULL)")
    Page<Event> findAllByParams(final League league, final Track track, final Pageable pageable);

//    Page<Event> findAllByLeagueAndTrack(final League league, final Track track, final Pageable pageable);

    @Query("SELECT e FROM Event e " +
            "LEFT JOIN Race r ON r.event = e " +
            "WHERE e.league = :league " +
            "AND r.startDate > NOW() " +
            "ORDER BY r.startDate ASC LIMIT 1")
    Event findNextEventByLeague(League league);

    @Query("SELECT e FROM Event e LEFT JOIN League l ON e.league = l " +
            "LEFT JOIN Game g ON l.game = g " +
            "WHERE g.gameFamily=:gameFamily")
    List<Event> findAllByGameFamily(GameFamily gameFamily);
}
