package com.sss.garage.dto.racepointdictionary;

import com.sss.garage.model.racepointtype.RacePointType;

import java.util.List;

public class RacePointDictionaryDTO {
    private Long id;

    private RacePointType racePointType;

    private List<Integer> points;

    private Boolean fastestLapScored;

    private Integer fastestLapPoints;

    private Boolean fastestLapForTop10;

    private Boolean polePositionScored;

    private Integer polePositionPoints;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RacePointType getRacePointType() {
        return racePointType;
    }

    public void setRacePointType(final RacePointType racePointType) {
        this.racePointType = racePointType;
    }

    public List<Integer> getPoints() {
        return points;
    }

    public void setPoints(final List<Integer> points) {
        this.points = points;
    }

    public Boolean getFastestLapScored() {
        return fastestLapScored;
    }

    public void setFastestLapScored(final Boolean fastestLapScored) {
        this.fastestLapScored = fastestLapScored;
    }

    public Integer getFastestLapPoints() {
        return fastestLapPoints;
    }

    public void setFastestLapPoints(final Integer fastestLapPoints) {
        this.fastestLapPoints = fastestLapPoints;
    }

    public Boolean getFastestLapForTop10() {
        return fastestLapForTop10;
    }

    public void setFastestLapForTop10(Boolean fastestLapForTop10) {
        this.fastestLapForTop10 = fastestLapForTop10;
    }

    public Boolean getPolePositionScored() {
        return polePositionScored;
    }

    public void setPolePositionScored(Boolean polePositionScored) {
        this.polePositionScored = polePositionScored;
    }

    public Integer getPolePositionPoints() {
        return polePositionPoints;
    }

    public void setPolePositionPoints(Integer polePositionPoints) {
        this.polePositionPoints = polePositionPoints;
    }
}
