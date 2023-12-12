package com.sss.garage.dto.pointcategory;

import com.sss.garage.dto.league.LeagueDTO;
import com.sss.garage.dto.pointposition.PointPositionDTO;

import java.util.Set;

public class PointCategoryDTO {
    private Long id;

    private String name;

    private Set<PointPositionDTO> positions;

    private Integer lapPoints;

    private Integer polePoints;

    private Set<LeagueDTO> leagues;

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

    public Set<PointPositionDTO> getPositions() {
        return positions;
    }

    public void setPositions(Set<PointPositionDTO> positions) {
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

    public Set<LeagueDTO> getLeagues() {
        return leagues;
    }

    public void setLeagues(Set<LeagueDTO> leagues) {
        this.leagues = leagues;
    }
}
