package com.sss.garage.model.pointposition;

import com.sss.garage.model.pointcategory.PointCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointPositionRepository extends JpaRepository<PointPosition, Long> {
    Page<PointPosition> findAllByCategory(final PointCategory pointCategory, final Pageable pageable);
}
