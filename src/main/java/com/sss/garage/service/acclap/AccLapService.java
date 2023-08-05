package com.sss.garage.service.acclap;

import com.sss.garage.model.acclap.AccLap;

import java.util.List;
import java.util.Optional;

public interface AccLapService {
    Optional<AccLap> getLap(final Long id);

    List<AccLap> getAllLaps();

    void createLap(final AccLap lap);

    void deleteLap(final Long id);
}
