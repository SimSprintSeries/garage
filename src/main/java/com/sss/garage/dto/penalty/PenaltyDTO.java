package com.sss.garage.dto.penalty;

import com.sss.garage.dto.driver.SimpleDriverDTO;
import com.sss.garage.dto.race.RaceDTO;

import java.util.Date;

public class PenaltyDTO {
    private Long id;

    private Integer penaltySeconds;

    private Integer penaltyPoints;

    private String incidentLink;

    private String incidentDescription;

    private String decisionDescription;

    private Date reportDate;

    private SimpleDriverDTO driver;

    private RaceDTO race;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPenaltySeconds() {
        return penaltySeconds;
    }

    public void setPenaltySeconds(Integer penaltySeconds) {
        this.penaltySeconds = penaltySeconds;
    }

    public Integer getPenaltyPoints() {
        return penaltyPoints;
    }

    public void setPenaltyPoints(Integer penaltyPoints) {
        this.penaltyPoints = penaltyPoints;
    }

    public String getIncidentLink() {
        return incidentLink;
    }

    public void setIncidentLink(String incidentLink) {
        this.incidentLink = incidentLink;
    }

    public String getIncidentDescription() {
        return incidentDescription;
    }

    public void setIncidentDescription(String incidentDescription) {
        this.incidentDescription = incidentDescription;
    }

    public String getDecisionDescription() {
        return decisionDescription;
    }

    public void setDecisionDescription(String decisionDescription) {
        this.decisionDescription = decisionDescription;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public SimpleDriverDTO getDriver() {
        return driver;
    }

    public void setDriver(SimpleDriverDTO driver) {
        this.driver = driver;
    }

    public RaceDTO getRace() {
        return race;
    }

    public void setRace(RaceDTO race) {
        this.race = race;
    }
}
