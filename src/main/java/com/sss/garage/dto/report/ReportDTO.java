package com.sss.garage.dto.report;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sss.garage.dto.driver.SimpleDriverDTO;
import com.sss.garage.dto.race.RaceDTO;

import java.util.Date;

public class ReportDTO {
    private Long id;

    private String incidentLink;

    private String incidentDescription;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date reportDate;

    private SimpleDriverDTO reportingDriver;

    private String reportingDriverId;

    private SimpleDriverDTO reportedDriver;

    private String reportedDriverId;

    private RaceDTO race;

    private String raceId;

    private Boolean checked;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getIncidentLink() {
        return incidentLink;
    }

    public void setIncidentLink(final String incidentLink) {
        this.incidentLink = incidentLink;
    }

    public String getIncidentDescription() {
        return incidentDescription;
    }

    public void setIncidentDescription(final String incidentDescription) {
        this.incidentDescription = incidentDescription;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(final Date reportDate) {
        this.reportDate = reportDate;
    }

    public SimpleDriverDTO getReportingDriver() {
        return reportingDriver;
    }

    public void setReportingDriver(final SimpleDriverDTO reportingDriver) {
        this.reportingDriver = reportingDriver;
    }

    public String getReportingDriverId() {
        return reportingDriverId;
    }

    public void setReportingDriverId(final String reportingDriverId) {
        this.reportingDriverId = reportingDriverId;
    }

    public SimpleDriverDTO getReportedDriver() {
        return reportedDriver;
    }

    public void setReportedDriver(final SimpleDriverDTO reportedDriver) {
        this.reportedDriver = reportedDriver;
    }

    public String getReportedDriverId() {
        return reportedDriverId;
    }

    public void setReportedDriverId(final String reportedDriverId) {
        this.reportedDriverId = reportedDriverId;
    }

    public RaceDTO getRace() {
        return race;
    }

    public void setRace(final RaceDTO race) {
        this.race = race;
    }

    public String getRaceId() {
        return raceId;
    }

    public void setRaceId(final String raceId) {
        this.raceId = raceId;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(final Boolean checked) {
        this.checked = checked;
    }
}
