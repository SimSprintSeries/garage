package com.sss.garage.dto.driver;

import com.sss.garage.dto.elo.EloDTO;
import com.sss.garage.dto.split.SplitDTO;

import java.util.Set;

public class DetailedDriverDTO extends SimpleDriverDTO {
    private Set<EloDTO> elos;

    private Set<SplitDTO> splits;

    private Integer totalWins;

    private Integer totalTopTenResults;

    private Integer totalRacesDriven;

    private Integer podiums;

    private Integer polePositions;

    private Integer fastestLaps;

    public Set<EloDTO> getElos() {
        return elos;
    }

    public void setElos(final Set<EloDTO> elos) {
        this.elos = elos;
    }

    public Set<SplitDTO> getSplits() {
        return splits;
    }

    public void setSplits(final Set<SplitDTO> splits) {
        this.splits = splits;
    }

    public Integer getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(final Integer totalWins) {
        this.totalWins = totalWins;
    }

    public Integer getTotalTopTenResults() {
        return totalTopTenResults;
    }

    public void setTotalTopTenResults(final Integer totalTopTenResults) {
        this.totalTopTenResults = totalTopTenResults;
    }

    public Integer getTotalRacesDriven() {
        return totalRacesDriven;
    }

    public void setTotalRacesDriven(final Integer totalRacesDriven) {
        this.totalRacesDriven = totalRacesDriven;
    }

    public Integer getPodiums() {
        return podiums;
    }

    public void setPodiums(final Integer podiums) {
        this.podiums = podiums;
    }

    public Integer getPolePositions() {
        return polePositions;
    }

    public void setPolePositions(final Integer polePositions) {
        this.polePositions = polePositions;
    }

    public Integer getFastestLaps() {
        return fastestLaps;
    }

    public void setFastestLaps(final Integer fastestLaps) {
        this.fastestLaps = fastestLaps;
    }
}
