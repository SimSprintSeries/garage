package com.sss.garage.facade.elo;

import java.util.List;
import java.util.Optional;

import com.sss.garage.data.elo.EloData;
import com.sss.garage.model.race.Race;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EloFacade {
    void calculateElo();
    void updateElo();
    void updateElo(List<Race> races);
    void updateEloSince(final Long raceId);
    Page<EloData> getElosPaginated(final String gameId, final Pageable pageable);
    Optional<EloData> getElo(final String driverId, final String gameId);
}
