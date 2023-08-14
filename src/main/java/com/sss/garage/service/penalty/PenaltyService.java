package com.sss.garage.service.penalty;

import com.sss.garage.model.penalty.Penalty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PenaltyService {
    Optional<Penalty> getPenalty(final Long id);

    List<Penalty> getAllPenalties();

    void createPenalty(final Penalty penalty);

    void deletePenalty(final Long id);

    Page<Penalty> getPenaltiesPaginated(final Pageable pageable);
}
