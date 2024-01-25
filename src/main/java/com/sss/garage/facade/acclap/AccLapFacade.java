package com.sss.garage.facade.acclap;

import com.sss.garage.data.acclap.AccLapData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccLapFacade {
    AccLapData getLap(final Long id);

    void createLap(final AccLapData lapData);

    void deleteLap(final Long id);

    Page<AccLapData> getFastestLapsForEveryDriver(final String sessionType, final String trackName, final String serverName, final Pageable pageable);
}
