package com.sss.garage.service.classification;

import com.sss.garage.model.classification.Classification;
import com.sss.garage.model.league.League;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClassificationService {
    Page<Classification> getClassification(final League league, final Pageable pageable);

    Page<Classification> getClassificationForTeams(final League league, final Pageable pageable);
}
