package com.sss.garage.facade.penalty;

import com.sss.garage.data.report.ReportData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReportFacade {
    ReportData getReport(final Long id);

    void createReport(final ReportData penalty);

    void deleteReport(final Long id);

    Page<ReportData> getReportsPaginated(final Boolean checked, final String reportingDriverId,
                                         final String reportedDriverId, final Pageable pageable);
}