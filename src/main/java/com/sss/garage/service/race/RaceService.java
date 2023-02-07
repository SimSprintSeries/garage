package com.sss.garage.service.race;

import java.util.List;

import com.sss.garage.model.race.Race;

public interface RaceService {
    List<Race> getAllRacesSorted();
    Boolean isQuali(final Race race);
}
