package com.sss.garage.model.report;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.game.Game;
import com.sss.garage.model.league.League;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    @Query("SELECT rep FROM Report rep LEFT JOIN Race r ON rep.race = r LEFT JOIN Event e ON r.event = e  " +
            "WHERE (rep.checked=:checked OR :checked IS NULL) " +
            "AND (rep.reportingDriver=:reportingDriver OR :reportingDriver IS NULL) " +
            "AND (rep.reportedDriver=:reportedDriver OR :reportedDriver IS NULL) " +
            "AND (e.league=:league OR :league IS NULL)")
    Page<Report> findAllByCheckedAndReportingDriverAndReportedDriver(Boolean checked, Driver reportingDriver,
                                                                     Driver reportedDriver, League league, Pageable pageable);

    @Query("SELECT SUM(rep.penaltyPoints) FROM Report rep " +
            "LEFT JOIN Race r ON rep.race = r " +
            "LEFT JOIN League l ON r.league = l " +
            "LEFT JOIN Game g ON l.game = g " +
            "WHERE r.startDate>=:date AND rep.reportedDriver=:driver AND g.gameFamily=:gameFamily")
    Integer findPenaltyPointsByDriverAndGameFamilyAndDate(final Driver driver, final Game gameFamily, final Date date);
}
