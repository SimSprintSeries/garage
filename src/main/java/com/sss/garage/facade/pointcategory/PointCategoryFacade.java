package com.sss.garage.facade.pointcategory;

import com.sss.garage.data.pointcategory.PointCategoryData;

import java.util.List;

public interface PointCategoryFacade {
    List<PointCategoryData> getAllPointCategories();

    PointCategoryData getPointCategory(final Long id);

    void createPointCategory(final PointCategoryData pointCategoryData);

    void deletePointCategory(final Long id);
}
