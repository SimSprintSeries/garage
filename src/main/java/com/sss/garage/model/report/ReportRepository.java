package com.sss.garage.model.report;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.league.League;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    @Query("SELECT rep FROM Report rep LEFT JOIN Race r ON rep.race = r LEFT JOIN Event e ON r.event = e  " +
            "WHERE (rep.checked=:checked OR :checked IS NULL) " +
            "AND (rep.reportingDriver=:reportingDriver OR :reportingDriver IS NULL) " +
            "AND (rep.reportedDriver=:reportedDriver OR :reportedDriver IS NULL) " +
            "AND (e.league=:league OR :league IS NULL)")
    Page<Report> findAllByCheckedAndReportingDriverAndReportedDriver(Boolean checked, Driver reportingDriver,
                                                                     Driver reportedDriver, League league, Pageable pageable);
}
