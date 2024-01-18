package com.sss.garage.facade.event;

import com.sss.garage.data.event.EventData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventFacade {
    Page<EventData> getAllEvents(final String leagueId, final String trackId, final Pageable pageable);
    EventData getEvent(final Long id);
    void createEvent(final EventData eventData);
    void deleteEvent(final Long id);
    EventData getNextEvent(final String leagueId);
}
