package com.sss.garage.facade.report.impl;

import com.sss.garage.data.race.RaceData;
import com.sss.garage.data.report.ReportData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.report.ReportFacade;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.league.League;
import com.sss.garage.model.race.Race;
import com.sss.garage.model.report.Report;
import com.sss.garage.service.driver.DriverService;
import com.sss.garage.service.league.LeagueService;
import com.sss.garage.service.race.RaceService;
import com.sss.garage.service.report.ReportService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class SssReportFacade extends SssBaseFacade implements ReportFacade {
    private ReportService reportService;

    private DriverService driverService;

    private LeagueService leagueService;

    private RaceService raceService;

    @Override
    public ReportData getReport(final Long id) {
        return reportService.getReport(id)
                .map(p -> conversionService.convert(p, ReportData.class))
                .orElseThrow();
    }

    @Override
    public void createReport(final String raceId, final ReportData report) {
        Race race = null;
        if(Strings.isNotEmpty(raceId)) {
            race = raceService.findById(Long.valueOf(raceId)).orElseThrow();
        }
        report.setReportDate(Date.from(Instant.now()));
        report.setRace(conversionService.convert(race, RaceData.class));
        reportService.createReport(conversionService.convert(report, Report.class));
    }

    @Override
    public void deleteReport(final Long id) {
        reportService.deleteReport(id);
    }

    @Override
    public Page<ReportData> getReportsPaginated(final Boolean checked, final String reportingDriverId,
                                                  final String reportedDriverId, final String leagueId, final Pageable pageable) {
        Driver reportingDriver = null;
        Driver reportedDriver = null;
        League league = null;
        if(Strings.isNotEmpty(reportingDriverId)) {
            reportingDriver = driverService.getDriver(Long.valueOf(reportingDriverId)).orElseThrow();
        }
        if(Strings.isNotEmpty(reportedDriverId)) {
            reportedDriver = driverService.getDriver(Long.valueOf(reportedDriverId)).orElseThrow();
        }
        if(Strings.isNotEmpty(leagueId)) {
            league = leagueService.getLeague(Long.valueOf(leagueId)).orElseThrow();
        }
        return reportService.getReportsPaginated(checked, reportingDriver, reportedDriver, league, pageable)
                .map(p -> conversionService.convert(p, ReportData.class));
    }

    @Override
    public void editReport(final Long id, final ReportData report) {
        report.setChecked(report.getDecisionDescription() != null);
        reportService.editReport(id, conversionService.convert(report,Report.class));
    }

    @Autowired
    public void setReportService(final ReportService reportService) {
        this.reportService = reportService;
    }

    @Autowired
    public void setDriverService(final DriverService driverService) {
        this.driverService = driverService;
    }

    @Autowired
    public void setLeagueService(final LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @Autowired
    public void setRaceService(final RaceService raceService) {
        this.raceService = raceService;
    }
}
