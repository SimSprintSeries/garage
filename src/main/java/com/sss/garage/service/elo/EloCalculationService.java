package com.sss.garage.service.elo;

import com.sss.garage.model.race.Race;

public interface EloCalculationService {
    void calculateAndSave(final Race race);
}
