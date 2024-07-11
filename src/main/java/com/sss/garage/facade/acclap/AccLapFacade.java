package com.sss.garage.facade.acclap;

import com.sss.garage.data.acclap.AccLapData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccLapFacade {
    AccLapData getLap(final Long id);

    Page<AccLapData> getFastestLapsForEveryDriver(final String sessionType, final String trackName, final String serverName, final String className, final Pageable pageable);
}
