package com.sss.garage.model.event;

import com.sss.garage.model.game.Game;
import com.sss.garage.model.league.League;
import com.sss.garage.model.race.Race;
import com.sss.garage.model.track.Track;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

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
            "AND e.startDate > NOW() " +
            "ORDER BY e.startDate ASC LIMIT 1")
    Event findNextEventByLeague(League league);

    @Query("SELECT e.startDate FROM Race r LEFT JOIN Event e ON r.event = e " +
            "LEFT JOIN League l ON e.league = l " +
            "LEFT JOIN Game g ON l.game = g " +
            "WHERE g.gameFamily=:gameFamily " +
            "GROUP BY g.gameFamily, WEEK(e.startDate), YEAR(e.startDate) " +
            "ORDER BY e.startDate DESC LIMIT 1 OFFSET 9")
    Date find10thEventDateByGameFamily(Game gameFamily);

    Event findFirstByLeagueOrderByStartDateAsc(League league);
}
