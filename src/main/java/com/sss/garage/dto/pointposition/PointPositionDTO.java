package com.sss.garage.dto.pointposition;

import com.sss.garage.dto.pointcategory.PointCategoryDTO;

public class PointPositionDTO {
    private Long id;

    private PointCategoryDTO category;

    private Integer position;

    private Integer points;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PointCategoryDTO getCategory() {
        return category;
    }

    public void setCategory(PointCategoryDTO category) {
        this.category = category;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
