package com.sss.garage.facade.pointcategory.impl;

import com.sss.garage.data.pointcategory.PointCategoryData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.pointcategory.PointCategoryFacade;
import com.sss.garage.model.pointcategory.PointCategory;
import com.sss.garage.service.pointcategory.PointCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SssPointCategoryFacade extends SssBaseFacade implements PointCategoryFacade {
    private PointCategoryService pointCategoryService;

    @Override
    public List<PointCategoryData> getAllPointCategories() {
        return pointCategoryService.getAllPointCategories().stream()
                .map(p -> conversionService.convert(p, PointCategoryData.class)).toList();
    }

    @Override
    public PointCategoryData getPointCategory(Long id) {
        return conversionService.convert(pointCategoryService.getPointCategory(id).orElseThrow(), PointCategoryData.class);
    }

    @Override
    public void createPointCategory(PointCategoryData pointCategoryData) {
        pointCategoryService.createPointCategory(conversionService.convert(pointCategoryData, PointCategory.class));
    }

    @Override
    public void deletePointCategory(Long id) {
        pointCategoryService.deletePointCategory(id);
    }

    @Autowired
    public void setPointCategoryService(PointCategoryService pointCategoryService) {
        this.pointCategoryService = pointCategoryService;
    }
}
