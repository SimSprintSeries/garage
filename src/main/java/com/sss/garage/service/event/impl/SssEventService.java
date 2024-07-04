package com.sss.garage.service.event.impl;

import com.sss.garage.model.event.Event;
import com.sss.garage.model.event.EventRepository;
import com.sss.garage.model.game.Game;
import com.sss.garage.model.league.League;
import com.sss.garage.model.race.Race;
import com.sss.garage.model.track.Track;
import com.sss.garage.service.event.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SssEventService implements EventService {

    private EventRepository eventRepository;

    @Override
    public Page<Event> getAllEvents(final League league, final Track track, final Pageable pageable) {
        System.out.println(eventRepository.find10thEventDateByGameFamily(league.getGame().getGameFamily()));

        return eventRepository.findAllByParams(league, track, pageable);
    }

    @Override
    public Event getEvent(final Race race) {
        return race.getEvent() != null ? race.getEvent() : race.getParentRaceEvent().getEvent();
    }

    @Override
    public Optional<Event> getEvent(final Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public void createEvent(final Event event) {
        eventRepository.save(event);
    }

    @Override
    public void createEvents(final List<Event> events) {
        eventRepository.saveAll(events);
    }

    @Override
    public void deleteEvent(final Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Event getNextEvent(final League league) {
        return eventRepository.findNextEventByLeague(league);
    }

    @Override
    public Date get10thEventDateByGameFamily(final Game gameFamily) {
        return eventRepository.find10thEventDateByGameFamily(gameFamily);
    }

    @Autowired
    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
}
