package com.sss.garage.model.role;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Driver {

    @Id
    private long id;
    private String name;


    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

}
