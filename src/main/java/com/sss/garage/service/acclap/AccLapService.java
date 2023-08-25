package com.sss.garage.service.acclap;

import com.sss.garage.model.acclap.AccLap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AccLapService {
    Optional<AccLap> getLap(final Long id);

    List<AccLap> getAllLaps();

    void createLap(final AccLap lap);

    void deleteLap(final Long id);

    Page<AccLap> getLapsPaginated(final String sessionType, final String trackName, final String serverName, final Pageable pageable);
}
