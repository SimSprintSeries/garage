package com.sss.garage.converter.report;

import com.sss.garage.converter.BaseConverter;
import com.sss.garage.data.report.ReportData;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.report.Report;
import com.sss.garage.model.race.Race;
import com.sss.garage.service.driver.DriverService;
import com.sss.garage.service.race.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class ReportReverseConverter extends BaseConverter implements Converter<ReportData, Report> {
    private RaceService raceService;

    private DriverService driverService;

    @Override
    public Report convert(final ReportData source) {
        final Report target = new Report();

        target.setId(source.getId());
        target.setPenaltySeconds(source.getPenaltySeconds());
        target.setPenaltyPoints(source.getPenaltyPoints());
        target.setIncidentLink(source.getIncidentLink());
        target.setIncidentDescription(source.getIncidentDescription());
        target.setDecisionDescription(source.getDecisionDescription());
        target.setReportingDriver(driverService.getDriver(Long.valueOf(source.getReportingDriverId())).orElseThrow());
        target.setReportedDriver(driverService.getDriver(Long.valueOf(source.getReportedDriverId())).orElseThrow());
        target.setRace(raceService.findById(Long.valueOf(source.getRaceId())).orElseThrow());
        target.setChecked(source.getChecked());
        target.setReportDate(Date.from(Instant.now()));

        return target;
    }

    @Autowired
    public void setRaceService(final RaceService raceService) {
        this.raceService = raceService;
    }

    @Autowired
    public void setDriverService(final DriverService driverService) {
        this.driverService = driverService;
    }
}
