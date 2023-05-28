package com.sss.garage.facade.race;

import com.sss.garage.data.race.RaceData;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RaceFacade {
    Page<RaceData> getRacesPaginated(final Boolean completed, final Pageable pageable);
    RaceData getRace(final Long id);
    List<RaceData> getAllRaces();
    void createRace(final RaceData raceData);
    void deleteRace(final Long id);
}
