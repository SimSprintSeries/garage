package com.sss.garage.service.raceresult;

import com.sss.garage.model.raceresult.RaceResult;

import java.util.List;
import java.util.Optional;

public interface RaceResultService {
    List<RaceResult> getAllRaceResults();

    Optional<RaceResult> getRaceResult(final Long id);

    void createRaceResult(final RaceResult raceResult);

    void deleteRaceResult(final Long id);
}
