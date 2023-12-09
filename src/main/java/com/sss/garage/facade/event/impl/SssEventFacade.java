package com.sss.garage.facade.event.impl;

import com.sss.garage.data.event.EventData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.event.EventFacade;
import com.sss.garage.model.event.Event;
import com.sss.garage.model.league.League;
import com.sss.garage.model.track.Track;
import com.sss.garage.service.event.EventService;
import com.sss.garage.service.league.LeagueService;
import com.sss.garage.service.track.TrackService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SssEventFacade extends SssBaseFacade implements EventFacade {
    private EventService eventService;

    private LeagueService leagueService;

    private TrackService trackService;

    @Override
    public Page<EventData> getAllEvents(final String leagueId, final String trackId, final Pageable pageable) {
        League league = null;
        Track track = null;
        if(Strings.isNotEmpty(leagueId)) {
            league = leagueService.getLeague(Long.valueOf(leagueId)).orElseThrow();
        }
        if(Strings.isNotEmpty(trackId)) {
            track = trackService.getTrack(Long.valueOf(trackId)).orElseThrow();
        }
        Page<Event> events = eventService.getAllEvents(league, track, pageable);
        return events.map(e -> conversionService.convert(e, EventData.class));
    }

    @Override
    public EventData getEvent(final Long id) {
        return eventService.getEvent(id)
                .map(e -> conversionService.convert(e, EventData.class))
                .orElseThrow();
    }

    @Override
    public void createEvent(EventData eventData) {
        eventService.createEvent(conversionService.convert(eventData, Event.class));
    }

    @Override
    public void deleteEvent(Long id) {
        eventService.deleteEvent(id);
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setLeagueService(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @Autowired
    public void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }
}
