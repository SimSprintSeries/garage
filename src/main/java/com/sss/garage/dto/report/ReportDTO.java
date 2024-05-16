package com.sss.garage.dto.report;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sss.garage.dto.driver.SimpleDriverDTO;

import java.util.Date;

public class ReportDTO {
    private Long id;

    private String incidentLink;

    private String incidentDescription;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date reportDate;

    private String reportingDriverId;

    private String reportedDriverId;

    private String raceId;

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

    public String getReportingDriverId() {
        return reportingDriverId;
    }

    public void setReportingDriverId(String reportingDriverId) {
        this.reportingDriverId = reportingDriverId;
    }

    public String getReportedDriverId() {
        return reportedDriverId;
    }

    public void setReportedDriverId(String reportedDriverId) {
        this.reportedDriverId = reportedDriverId;
    }

    public String getRaceId() {
        return raceId;
    }

    public void setRaceId(String raceId) {
        this.raceId = raceId;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
