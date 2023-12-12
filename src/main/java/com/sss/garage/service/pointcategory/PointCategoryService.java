package com.sss.garage.service.pointcategory;

import com.sss.garage.model.pointcategory.PointCategory;

import java.util.List;
import java.util.Optional;

public interface PointCategoryService {
    List<PointCategory> getAllPointCategories();

    Optional<PointCategory> getPointCategory(final Long id);

    void createPointCategory(final PointCategory pointCategory);

    void deletePointCategory(final Long id);
}
