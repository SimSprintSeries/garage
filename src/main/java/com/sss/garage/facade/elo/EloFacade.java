package com.sss.garage.facade.elo;

import java.util.List;

import com.sss.garage.model.race.Race;

public interface EloFacade {
    void calculateElo();
    void updateElo();
    void updateElo(List<Race> races);
    void updateEloSince(final Long raceId);
}
