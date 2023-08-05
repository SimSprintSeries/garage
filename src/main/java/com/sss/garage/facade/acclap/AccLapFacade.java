package com.sss.garage.facade.acclap;

import com.sss.garage.data.acclap.AccLapData;

import java.util.List;

public interface AccLapFacade {
    AccLapData getLap(final Long id);

    List<AccLapData> getAllLaps();

    void createLap(final AccLapData lapData);

    void deleteLap(final Long id);
}
