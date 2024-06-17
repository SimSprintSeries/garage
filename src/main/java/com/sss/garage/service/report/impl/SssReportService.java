package com.sss.garage.service.report.impl;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.game.Game;
import com.sss.garage.model.league.League;
import com.sss.garage.model.report.Report;
import com.sss.garage.model.report.ReportRepository;
import com.sss.garage.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class SssReportService implements ReportService {
    private ReportRepository reportRepository;

    @Override
    public Optional<Report> getReport(final Long id) {
        return reportRepository.findById(id);
    }

    @Override
    public void createReport(final Report report) {
        reportRepository.save(report);
    }

    @Override
    public void deleteReport(final Long id) {
        reportRepository.deleteById(id);
    }

    @Override
    public Page<Report> getReportsPaginated(final Boolean checked, final Driver reportingDriver,
                                            final Driver reportedDriver, final League league, final Pageable pageable) {
        return reportRepository.findAllByCheckedAndReportingDriverAndReportedDriver(checked, reportingDriver,
                reportedDriver, league, pageable);
    }

    @Override
    public void editReport(final Long id, final Report report) {
        Report newReport = reportRepository.findById(id).orElseThrow();
        newReport.setDecisionDescription(report.getDecisionDescription());
        newReport.setPenaltyPoints(report.getPenaltyPoints());
        newReport.setPenaltySeconds(report.getPenaltySeconds());
        newReport.setChecked(report.getChecked());
        reportRepository.save(newReport);
    }

    @Override
    public Integer getPenaltyPoints(final Driver driver, final Game gameFamily, final Date date) {
        return reportRepository.findPenaltyPointsByDriverAndGameFamilyAndDate(driver, gameFamily, date);
    }

    @Autowired
    public void setReportRepository(final ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }
}
