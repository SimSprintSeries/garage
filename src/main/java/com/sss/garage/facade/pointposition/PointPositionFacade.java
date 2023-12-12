package com.sss.garage.facade.pointposition;

import com.sss.garage.data.pointposition.PointPositionData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PointPositionFacade {
    Page<PointPositionData> getAllPointPositions(final String categoryId, final Pageable pageable);

    PointPositionData getPointPosition(final Long id);

    void createPointPosition(final PointPositionData pointPositionData);

    void deletePointPosition(final Long id);
}
