package com.sss.garage.data.penalty;

import com.sss.garage.data.driver.DriverData;
import com.sss.garage.data.race.RaceData;

import java.util.Date;

public class PenaltyData {
    private Long id;

    private Integer penaltySeconds;

    private Integer penaltyPoints;

    private String incidentLink;

    private String incidentDescription;

    private String decisionDescription;

    private Date reportDate;

    private DriverData driver;

    private RaceData race;

    private Boolean checked;

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

    public DriverData getDriver() {
        return driver;
    }

    public void setDriver(DriverData driver) {
        this.driver = driver;
    }

    public RaceData getRace() {
        return race;
    }

    public void setRace(RaceData race) {
        this.race = race;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
