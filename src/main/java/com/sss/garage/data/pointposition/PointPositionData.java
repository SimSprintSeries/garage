package com.sss.garage.data.pointposition;

import com.sss.garage.data.pointcategory.PointCategoryData;

public class PointPositionData {
    private Long id;

    private PointCategoryData category;

    private Integer position;

    private Integer points;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PointCategoryData getCategory() {
        return category;
    }

    public void setCategory(PointCategoryData category) {
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
