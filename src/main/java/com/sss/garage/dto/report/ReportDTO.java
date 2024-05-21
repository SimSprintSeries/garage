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

    private SimpleDriverDTO reportedDriver;

    private RaceDTO race;

    private Boolean checked;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public SimpleDriverDTO getReportingDriver() {
        return reportingDriver;
    }

    public void setReportingDriver(SimpleDriverDTO reportingDriver) {
        this.reportingDriver = reportingDriver;
    }

    public SimpleDriverDTO getReportedDriver() {
        return reportedDriver;
    }

    public void setReportedDriver(SimpleDriverDTO reportedDriver) {
        this.reportedDriver = reportedDriver;
    }

    public RaceDTO getRace() {
        return race;
    }

    public void setRace(RaceDTO race) {
        this.race = race;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
