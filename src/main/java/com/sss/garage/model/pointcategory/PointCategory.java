package com.sss.garage.model.pointcategory;

import com.sss.garage.model.league.League;
import com.sss.garage.model.pointposition.PointPosition;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class PointCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private Set<PointPosition> positions;

    private Integer lapPoints;

    private Integer polePoints;

    @OneToMany(mappedBy = "category")
    private Set<League> leagues;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PointPosition> getPositions() {
        return positions;
    }

    public void setPositions(Set<PointPosition> positions) {
        this.positions = positions;
    }

    public Integer getLapPoints() {
        return lapPoints;
    }

    public void setLapPoints(Integer lapPoints) {
        this.lapPoints = lapPoints;
    }

    public Integer getPolePoints() {
        return polePoints;
    }

    public void setPolePoints(Integer polePoints) {
        this.polePoints = polePoints;
    }

    public Set<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(Set<League> leagues) {
        this.leagues = leagues;
    }
}
