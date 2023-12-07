package com.sss.garage.model.event;

import com.sss.garage.model.league.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Integer countByLeague(League league);

    Event findFirstByLeagueOrderByStartDateAsc(League league);
}
