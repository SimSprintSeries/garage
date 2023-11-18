package com.sss.garage.model.raceresult;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.game.Game;
import com.sss.garage.model.league.League;
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

    @Query("SELECT COUNT(*) FROM RaceResult rr LEFT JOIN Race r ON rr.race = r LEFT JOIN Event e ON r.event = e LEFT JOIN League l ON e.league = l " +
            "LEFT JOIN Split s ON r.split = s " +
            "WHERE (rr.driver=:driver)" +
            "AND (e.league=:league OR :league IS NULL)" +
            "AND (l.game=:game OR :game IS NULL)" +
            "AND (s.split=:split OR :split IS NULL)" +
            "AND rr.finishPosition BETWEEN :pos1 AND :pos2")
    Integer countFinishPositionByParams(Driver driver, League league, Game game, String split, Integer pos1, Integer pos2);

    @Query("SELECT COUNT(*) FROM RaceResult rr LEFT JOIN Race r ON rr.race = r LEFT JOIN Event e ON r.event = e LEFT JOIN League l ON e.league = l " +
            "LEFT JOIN Split s ON r.split = s " +
            "WHERE (rr.driver=:driver)" +
            "AND (e.league=:league OR :league IS NULL)" +
            "AND (l.game=:game OR :game IS NULL)" +
            "AND (s.split=:split OR :split IS NULL)" +
            "AND rr.dnf=true")
    Integer countDnfByParams(Driver driver, League league, Game game, String split);

    @Query("SELECT COUNT(*) FROM RaceResult rr LEFT JOIN Race r ON rr.race = r LEFT JOIN Event e ON r.event = e LEFT JOIN League l ON e.league = l " +
            "LEFT JOIN Split s ON r.split = s " +
            "WHERE (rr.driver=:driver)" +
            "AND (e.league=:league OR :league IS NULL)" +
            "AND (l.game=:game OR :game IS NULL)" +
            "AND (s.split=:split OR :split IS NULL)" +
            "AND rr.dsq=true")
    Integer countDsqByParams(Driver driver, League league, Game game, String split);

    @Query("SELECT COUNT(*) FROM RaceResult rr LEFT JOIN Race r ON rr.race = r LEFT JOIN Event e ON r.event = e LEFT JOIN League l ON e.league = l " +
            "LEFT JOIN Split s ON r.split = s " +
            "WHERE (rr.driver=:driver)" +
            "AND (e.league=:league OR :league IS NULL)" +
            "AND (l.game=:game OR :game IS NULL)" +
            "AND (s.split=:split OR :split IS NULL)" +
            "AND rr.fastestLap=true")
    Integer countFlByParams(Driver driver, League league, Game game, String split);
}
