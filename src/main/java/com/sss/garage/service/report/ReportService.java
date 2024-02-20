package com.sss.garage.service.report;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.league.League;
import com.sss.garage.model.report.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ReportService {
    Optional<Report> getReport(final Long id);

    void createReport(final Report report);

    void deleteReport(final Long id);

    Page<Report> getReportsPaginated(final Boolean checked, final Driver reportingDriver,
                                     final Driver reportedDriver, final League league, final Pageable pageable);
}
