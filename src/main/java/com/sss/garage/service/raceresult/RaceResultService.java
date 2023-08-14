package com.sss.garage.service.raceresult;

import com.sss.garage.model.raceresult.RaceResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RaceResultService {
    List<RaceResult> getAllRaceResults();

    Optional<RaceResult> getRaceResult(final Long id);

    void createRaceResult(final RaceResult raceResult);

    void deleteRaceResult(final Long id);

    Page<RaceResult> getRaceResultsPaginated(final Pageable pageable);
}
