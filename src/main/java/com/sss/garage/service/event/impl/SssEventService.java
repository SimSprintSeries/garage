package com.sss.garage.service.event.impl;

import com.sss.garage.model.event.Event;
import com.sss.garage.model.event.EventRepository;
import com.sss.garage.model.game.family.GameFamily;
import com.sss.garage.model.game.family.GameFamilyRepository;
import com.sss.garage.model.league.League;
import com.sss.garage.model.race.Race;
import com.sss.garage.model.track.Track;
import com.sss.garage.service.event.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class SssEventService implements EventService {

    private EventRepository eventRepository;

    private GameFamilyRepository gameFamilyRepository;

    @Override
    public Page<Event> getAllEvents(final League league, final Track track, final Pageable pageable) {
        List<Event> events = getLast10Events(gameFamilyRepository.findById(1).orElseThrow());
        for (Event event : events) {
            System.out.println(event.getName() + event.getStartDate());
        }
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
    public void createEvent(Event event) {
        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Event getNextEvent(final League league) {
        return eventRepository.findNextEventByLeague(league);
    }

    private List<Event> getLast10Events(final GameFamily gameFamily) {
        return eventRepository.findAllByGameFamily(gameFamily).stream().filter(e -> LocalDateTime.now()
                        .isAfter(LocalDateTime.ofInstant(e.getStartDate().toInstant(), ZoneId.systemDefault())))
                .sorted(Comparator.comparing(Event::getStartDate).reversed()).limit(10).toList();
    }

    @Autowired
    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Autowired
    public void setGameFamilyRepository(GameFamilyRepository gameFamilyRepository) {
        this.gameFamilyRepository = gameFamilyRepository;
    }
}
