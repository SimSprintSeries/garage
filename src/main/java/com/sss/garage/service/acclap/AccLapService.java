package com.sss.garage.service.acclap;

import com.sss.garage.model.acclap.AccLap;
import com.sss.garage.model.league.League;
import com.sss.garage.model.track.Track;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AccLapService {
    Optional<AccLap> getLap(final Long id);

    Page<AccLap> getFastestLapsForEveryDriver(final String sessionType, final Track track,
                                              final League league, final String className, final Pageable pageable);
}
