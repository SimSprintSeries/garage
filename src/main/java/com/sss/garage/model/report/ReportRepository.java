package com.sss.garage.model.report;

import com.sss.garage.model.driver.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    @Query("SELECT r FROM Report r " +
            "WHERE (r.checked=:checked OR :checked IS NULL) " +
            "AND (r.reportingDriver=:reportingDriver OR :reportingDriver IS NULL) " +
            "AND (r.reportedDriver=:reportedDriver OR :reportedDriver IS NULL) ")
    Page<Report> findAllByCheckedAndReportingDriverAndReportedDriver(Boolean checked, Driver reportingDriver,
                                                                     Driver reportedDriver, Pageable pageable);

    Page<Report> findAllByReportingDriverAndReportedDriver(Driver reportingDriver, Driver reportedDriver, Pageable pageable);
}
