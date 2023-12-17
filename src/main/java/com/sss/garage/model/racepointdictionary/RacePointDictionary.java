package com.sss.garage.model.racepointdictionary;

import java.util.Collections;
import java.util.List;

import com.sss.garage.model.racepointtype.RacePointType;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "racePointType" }) })
public class RacePointDictionary {

    public RacePointDictionary() {
    }

    public RacePointDictionary(final RacePointType racePointType, final List<Integer> points,
                               final Boolean fastestLapScored, final Integer fastestLapPoints) {
        this.racePointType = racePointType;
        this.points = points;
        this.fastestLapScored = fastestLapScored;
        this.fastestLapPoints = fastestLapPoints;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private RacePointType racePointType;

    @ElementCollection
    @OrderColumn
    private List<Integer> points;

    private Boolean fastestLapScored;

    private Integer fastestLapPoints;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public RacePointType getRacePointType() {
        return racePointType;
    }

    public void setRacePointType(final RacePointType racePointType) {
        this.racePointType = racePointType;
    }

    public void setPoints(final List<Integer> points) {
        points.removeAll(Collections.singleton(0)); // remove all 0 point scoring positions in the list
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

    /**
     * Returns how many points specified position earned in this type of point scoring
     * @param position !!!!! NON-ZERO-INDEXED DRIVER RESULT !!!!!
     */
    public Integer pointsForPosition(final Integer position) {
        if(position > points.size() || position < 1) {
            return 0;
        }
        return points.get(position - 1);
    }
}
