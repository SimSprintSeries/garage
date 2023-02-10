package com.sss.garage.model.elo.history;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.elo.Elo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EloHistoryRepository extends JpaRepository<Elo, Long> {
    @Query("SELECT e FROM Elo e WHERE TYPE(e)=EloHistory AND e.driver=:driver")
    List<EloHistory> findHistoryByDriver(Driver driver, Sort startDate);

    @Query("SELECT e FROM EloHistory e WHERE TYPE(e)=EloHistory AND e.validUntil=:startDate AND e.driver=:driver")
    List<Elo> findByStartDateAndDriver(Date startDate, Driver driver);
}
