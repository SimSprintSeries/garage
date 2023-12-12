package com.sss.garage.service.pointposition.impl;

import com.sss.garage.model.pointcategory.PointCategory;
import com.sss.garage.model.pointposition.PointPosition;
import com.sss.garage.model.pointposition.PointPositionRepository;
import com.sss.garage.service.pointposition.PointPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SssPointPositionService implements PointPositionService {
    private PointPositionRepository pointPositionRepository;

    @Override
    public Page<PointPosition> getAllPointPositions(final PointCategory pointCategory, final Pageable pageable) {
        return pointPositionRepository.findAllByCategory(pointCategory, pageable);
    }

    @Override
    public Optional<PointPosition> getPointPosition(Long id) {
        return pointPositionRepository.findById(id);
    }

    @Override
    public void createPointPosition(PointPosition pointPosition) {
        pointPositionRepository.save(pointPosition);
    }

    @Override
    public void deletePointPosition(Long id) {
        pointPositionRepository.deleteById(id);
    }

    @Autowired
    public void setPointPositionRepository(PointPositionRepository pointPositionRepository) {
        this.pointPositionRepository = pointPositionRepository;
    }
}
