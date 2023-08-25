package com.sss.garage.model.raceresult;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.race.Race;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceResultRepository extends JpaRepository<RaceResult, Long> {
    @Query("SELECT r FROM RaceResult r WHERE (r.finishPosition=:finishPosition OR :finishPosition IS NULL) " +
            "AND (r.polePosition=:polePosition OR :polePosition IS NULL)" +
            "AND (r.dnf=:dnf OR :dnf IS NULL)" +
            "AND (r.dsq=:dsq OR :dsq IS NULL)" +
            "AND (r.fastestLap=:fastestLap OR :fastestLap IS NULL)" +
            "AND (r.driver=:driver OR :driver IS NULL)" +
            "AND (r.race=:race OR :race IS NULL)")
    Page<RaceResult> findAllByParams(String finishPosition, Boolean polePosition, Boolean dnf, Boolean dsq
            , Boolean fastestLap, Driver driver, Race race, Pageable pageable);

}
