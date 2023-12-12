package com.sss.garage.data.pointcategory;

import com.sss.garage.data.league.LeagueData;
import com.sss.garage.data.pointposition.PointPositionData;

import java.util.Set;

public class PointCategoryData {
    private Long id;

    private String name;

    private Set<PointPositionData> positions;

    private Integer polePoints;

    private Integer lapPoints;

    private Set<LeagueData> leagues;

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

    public Set<PointPositionData> getPositions() {
        return positions;
    }

    public void setPositions(Set<PointPositionData> positions) {
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

    public Set<LeagueData> getLeagues() {
        return leagues;
    }

    public void setLeagues(Set<LeagueData> leagues) {
        this.leagues = leagues;
    }
}
