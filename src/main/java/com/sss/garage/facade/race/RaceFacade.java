package com.sss.garage.facade.race;

import com.sss.garage.data.race.RaceData;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RaceFacade {
    Page<RaceData> getRacesPaginated(final Boolean completed, final Pageable pageable);
}
