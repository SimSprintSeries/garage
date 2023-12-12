package com.sss.garage.model.pointcategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointCategoryRepository extends JpaRepository<PointCategory, Long> {
}
