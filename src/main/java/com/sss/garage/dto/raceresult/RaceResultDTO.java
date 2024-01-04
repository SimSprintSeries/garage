package com.sss.garage.dto.raceresult;

import com.sss.garage.dto.driver.SimpleDriverDTO;
import com.sss.garage.dto.race.RaceDTO;
import com.sss.garage.dto.team.TeamDTO;

public class RaceResultDTO {
    private Long id;

    private Integer finishPosition;

    private Boolean polePosition;

    private Boolean dnf;

    private Boolean dsq;

    private Boolean fastestLap;

    private SimpleDriverDTO driverDTO;

    private RaceDTO raceDTO;

    private TeamDTO teamDTO;

    private Integer pointsForPosition;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Integer getFinishPosition() {
        return finishPosition;
    }

    public void setFinishPosition(final Integer finishPosition) {
        this.finishPosition = finishPosition;
    }

    public Boolean getPolePosition() {
        return polePosition;
    }

    public void setPolePosition(final Boolean polePosition) {
        this.polePosition = polePosition;
    }

    public Boolean getDnf() {
        return dnf;
    }

    public void setDnf(final Boolean dnf) {
        this.dnf = dnf;
    }

    public Boolean getDsq() {
        return dsq;
    }

    public void setDsq(final Boolean dsq) {
        this.dsq = dsq;
    }

    public Boolean getFastestLap() {
        return fastestLap;
    }

    public void setFastestLap(final Boolean fastestLap) {
        this.fastestLap = fastestLap;
    }

    public SimpleDriverDTO getDriver() {
        return driverDTO;
    }

    public void setDriver(SimpleDriverDTO driverDTO) {
        this.driverDTO = driverDTO;
    }

    public RaceDTO getRace() {
        return raceDTO;
    }

    public void setRace(RaceDTO raceDTO) {
        this.raceDTO = raceDTO;
    }

    public TeamDTO getTeam() {
        return teamDTO;
    }

    public void setTeam(TeamDTO teamDTO) {
        this.teamDTO = teamDTO;
    }

    public Integer getPointsForPosition() {
        return pointsForPosition;
    }

    public void setPointsForPosition(Integer pointsForPosition) {
        this.pointsForPosition = pointsForPosition;
    }
}
