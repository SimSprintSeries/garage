package com.sss.garage.facade.pointposition.impl;

import com.sss.garage.data.pointposition.PointPositionData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.pointposition.PointPositionFacade;
import com.sss.garage.model.pointcategory.PointCategory;
import com.sss.garage.model.pointposition.PointPosition;
import com.sss.garage.service.pointcategory.PointCategoryService;
import com.sss.garage.service.pointposition.PointPositionService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SssPointPositionFacade extends SssBaseFacade implements PointPositionFacade {
    private PointPositionService pointPositionService;

    private PointCategoryService pointCategoryService;

    @Override
    public Page<PointPositionData> getAllPointPositions(String categoryId, Pageable pageable) {
        PointCategory pointCategory = null;
        if(Strings.isNotEmpty(categoryId)) {
            pointCategory = pointCategoryService.getPointCategory(Long.valueOf(categoryId)).orElseThrow();
        }
        Page<PointPosition> pointPositions = pointPositionService.getAllPointPositions(pointCategory, pageable);
        return pointPositions.map(p -> conversionService.convert(p, PointPositionData.class));
    }

    @Override
    public PointPositionData getPointPosition(Long id) {
        return conversionService.convert(pointPositionService.getPointPosition(id).orElseThrow(), PointPositionData.class);
    }

    @Override
    public void createPointPosition(PointPositionData pointPositionData) {
        pointPositionService.createPointPosition(conversionService.convert(pointPositionData, PointPosition.class));
    }

    @Override
    public void deletePointPosition(Long id) {
        pointPositionService.deletePointPosition(id);
    }

    @Autowired
    public void setPointPositionService(PointPositionService pointPositionService) {
        this.pointPositionService = pointPositionService;
    }

    @Autowired
    public void setPointCategoryService(PointCategoryService pointCategoryService) {
        this.pointCategoryService = pointCategoryService;
    }
}
