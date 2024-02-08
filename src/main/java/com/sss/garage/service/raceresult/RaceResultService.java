package com.sss.garage.service.raceresult;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.race.Race;
import com.sss.garage.model.raceresult.RaceResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RaceResultService {
    Optional<RaceResult> getRaceResult(final Long id);

    void createRaceResult(final RaceResult raceResult);

    void deleteRaceResult(final Long id);

    Page<RaceResult> getRaceResultsPaginated(String finishPosition, Boolean polePosition, Boolean dnf, Boolean dsq
            , Boolean fastestLap, Driver driver, Race race, Pageable pageable);

    Integer calculateAndSaveTotalDriverWins(final Driver driver);

    Integer calculateAndSaveTotalDriverTopTenResults(final Driver driver);

    Integer calculateAndSaveTotalRacesDriven(final Driver driver);

    Integer calculateAndSaveTotalPodiums(final Driver driver);

    Integer calculateAndSaveFastestLaps(final Driver driver);

    Integer calculateAndSavePolePositions(final Driver driver);
}
