package com.sss.garage.service.pointposition;

import com.sss.garage.model.pointcategory.PointCategory;
import com.sss.garage.model.pointposition.PointPosition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PointPositionService {
    Page<PointPosition> getAllPointPositions(final PointCategory pointCategory, final Pageable pageable);

    Optional<PointPosition> getPointPosition(final Long id);

    void createPointPosition(final PointPosition pointPosition);

    void deletePointPosition(final Long id);
}
