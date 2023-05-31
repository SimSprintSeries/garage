package com.sss.garage.facade.raceresult;

import com.sss.garage.data.raceresult.RaceResultData;

import java.util.List;

public interface RaceResultFacade {
    RaceResultData getRaceResult(final Long id);

    List<RaceResultData> getAllRaceResults();

    void createRaceResult(final RaceResultData raceResultData);

    void deleteRaceResult(final Long id);
}
