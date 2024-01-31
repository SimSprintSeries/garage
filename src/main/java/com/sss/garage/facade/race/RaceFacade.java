package com.sss.garage.facade.race;

import com.sss.garage.data.race.RaceData;

import com.sss.garage.model.event.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RaceFacade {
    Page<RaceData> getRacesPaginated(final String leagueId, final Boolean completed, final Pageable pageable);
    RaceData getRace(final Long id);
    void createRace(final RaceData raceData);
    void deleteRace(final Long id);
    Page<RaceData> getAllRacesByEvent(final String eventId, final Pageable pageable);
}
