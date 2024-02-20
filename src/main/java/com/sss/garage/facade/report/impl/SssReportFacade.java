package com.sss.garage.facade.report.impl;

import com.sss.garage.data.report.ReportData;
import com.sss.garage.facade.SssBaseFacade;
import com.sss.garage.facade.report.ReportFacade;
import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.league.League;
import com.sss.garage.model.report.Report;
import com.sss.garage.service.driver.DriverService;
import com.sss.garage.service.league.LeagueService;
import com.sss.garage.service.report.ReportService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SssReportFacade extends SssBaseFacade implements ReportFacade {
    private ReportService reportService;

    private DriverService driverService;

    private LeagueService leagueService;

    @Override
    public ReportData getReport(final Long id) {
        return reportService.getReport(id)
                .map(p -> conversionService.convert(p, ReportData.class))
                .orElseThrow();
    }

    @Override
    public void createReport(final ReportData penalty) {
        reportService.createReport(conversionService.convert(penalty, Report.class));
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

    @Autowired
    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }

    @Autowired
    public void setDriverService(DriverService driverService) {
        this.driverService = driverService;
    }

    @Autowired
    public void setLeagueService(LeagueService leagueService) {
        this.leagueService = leagueService;
    }
}
