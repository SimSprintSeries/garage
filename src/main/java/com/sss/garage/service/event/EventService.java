package com.sss.garage.service.event;

import com.sss.garage.model.event.Event;
import com.sss.garage.model.league.League;
import com.sss.garage.model.race.Race;
import com.sss.garage.model.track.Track;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EventService {
    Page<Event> getAllEvents(final League league, final Track track, final Pageable pageable);
    Event getEvent(final Race race);
    Optional<Event> getEvent(final Long id);
    void createEvent(final Event event);
    void deleteEvent(final Long id);
    Event getNextEvent(final League league);
}
