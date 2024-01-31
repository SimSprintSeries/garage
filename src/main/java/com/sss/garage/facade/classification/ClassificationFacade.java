package com.sss.garage.facade.classification;

import com.sss.garage.data.classification.ClassificationData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClassificationFacade {
    Page<ClassificationData> getClassification(final String leagueId, final Pageable pageable);

    Page<ClassificationData> getClassificationForTeams(final String leagueId, final Pageable pageable);
}
