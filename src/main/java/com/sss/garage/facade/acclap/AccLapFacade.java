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

    Page<AccLapData> getLapsPaginated(final Pageable pageable);
}
