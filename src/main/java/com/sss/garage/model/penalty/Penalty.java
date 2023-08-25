package com.sss.garage.model.penalty;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.race.Race;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Penalty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer penaltySeconds;

    private Integer penaltyPoints;

    private String incidentLink;

    private String incidentDescription;

    private String decisionDescription;

    private Date reportDate;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Race race;

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

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
