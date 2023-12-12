package com.sss.garage.service.pointcategory.impl;

import com.sss.garage.model.pointcategory.PointCategory;
import com.sss.garage.model.pointcategory.PointCategoryRepository;
import com.sss.garage.service.pointcategory.PointCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SssPointCategoryService implements PointCategoryService {
    private PointCategoryRepository pointCategoryRepository;

    @Override
    public List<PointCategory> getAllPointCategories() {
        return pointCategoryRepository.findAll();
    }

    @Override
    public Optional<PointCategory> getPointCategory(Long id) {
        return pointCategoryRepository.findById(id);
    }

    @Override
    public void createPointCategory(PointCategory pointCategory) {
        pointCategoryRepository.save(pointCategory);
    }

    @Override
    public void deletePointCategory(Long id) {
        pointCategoryRepository.deleteById(id);
    }

    @Autowired
    public void setPointCategoryRepository(PointCategoryRepository pointCategoryRepository) {
        this.pointCategoryRepository = pointCategoryRepository;
    }
}
