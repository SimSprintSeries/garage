package com.sss.garage.facade.race;

import com.sss.garage.data.race.RaceData;

import com.sss.garage.model.event.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RaceFacade {
    RaceData getRace(final Long id);
    void createRace(final RaceData raceData);
    void createRaces(final List<RaceData> racesData, final String eventId);
    void deleteRace(final Long id);
    Page<RaceData> getAllRacesByEvent(final String eventId, final Pageable pageable);
}
