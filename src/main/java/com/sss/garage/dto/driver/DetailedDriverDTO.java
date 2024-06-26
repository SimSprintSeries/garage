package com.sss.garage.dto.driver;

import com.sss.garage.dto.elo.EloDTO;
import com.sss.garage.dto.league.LeagueDTO;
import com.sss.garage.dto.split.SplitDTO;

import java.util.Set;

public class DetailedDriverDTO extends SimpleDriverDTO {
    private Set<EloDTO> elos;

    private Set<LeagueDTO> leagues;

    private Integer totalWins;

    private Integer totalTopTenResults;

    private Integer totalRacesDriven;

    private Integer podiums;

    private Integer polePositions;

    private Integer fastestLaps;

    private Integer penaltyPointsF1;

    private Integer penaltyPointsAC;

    public Set<EloDTO> getElos() {
        return elos;
    }

    public void setElos(final Set<EloDTO> elos) {
        this.elos = elos;
    }

    public Set<LeagueDTO> getLeagues() {
        return leagues;
    }

    public void setLeagues(final Set<LeagueDTO> leagues) {
        this.leagues = leagues;
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

    public Integer getPenaltyPointsF1() {
        return penaltyPointsF1;
    }

    public void setPenaltyPointsF1(final Integer penaltyPointsF1) {
        this.penaltyPointsF1 = penaltyPointsF1;
    }

    public Integer getPenaltyPointsAC() {
        return penaltyPointsAC;
    }

    public void setPenaltyPointsAC(final Integer penaltyPointsAC) {
        this.penaltyPointsAC = penaltyPointsAC;
    }
}
