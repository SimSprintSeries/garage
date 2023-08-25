package com.sss.garage.facade.raceresult;

import com.sss.garage.data.raceresult.RaceResultData;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.race.Race;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RaceResultFacade {
    RaceResultData getRaceResult(final Long id);

    List<RaceResultData> getAllRaceResults();

    void createRaceResult(final RaceResultData raceResultData);

    void deleteRaceResult(final Long id);

    Page<RaceResultData> getRaceResultsPaginated(final String finishPosition, final Boolean polePosition, final Boolean dnf, final Boolean dsq
            , final Boolean fastestLap, final String driverId, final String raceId, final Pageable pageable);
}
