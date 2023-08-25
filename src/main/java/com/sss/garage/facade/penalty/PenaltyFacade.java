package com.sss.garage.facade.penalty;

import com.sss.garage.data.penalty.PenaltyData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PenaltyFacade {
    PenaltyData getPenalty(final Long id);

    List<PenaltyData> getAllPenalties();

    void createPenalty(final PenaltyData penalty);

    void deletePenalty(final Long id);

    Page<PenaltyData> getPenaltiesPaginated(final Boolean checked, final Pageable pageable);
}
