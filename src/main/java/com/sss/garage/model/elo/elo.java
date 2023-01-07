package com.sss.garage.model.role;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class raceResults {

    @Id
    private String id;
    private int finishPosition;
    private boolean polePosition;
    private boolean dnf;
    private boolean fastestLap;
    

    private String code;

    @Override
    public String getAuthority() { return getId(); }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

}
