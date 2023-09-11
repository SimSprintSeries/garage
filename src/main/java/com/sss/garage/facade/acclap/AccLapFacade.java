package com.sss.garage.facade.acclap;

import com.sss.garage.data.acclap.AccLapData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccLapFacade {
    AccLapData getLap(final Long id);

    List<AccLapData> getAllLaps();

    void createLap(final AccLapData lapData);

    void deleteLap(final Long id);

    Page<AccLapData> getLapsPaginated(final String sessionType, final String trackName, final String serverName, final Pageable pageable);

    Page<AccLapData> getFastestLapsForEveryDriver(final String sessionType, final String trackName, final String serverName, final Pageable pageable);
}
