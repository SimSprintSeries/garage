package com.sss.garage.model.cartable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CarTable {
    @Id
    private Integer id;

    private String carModel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
}
