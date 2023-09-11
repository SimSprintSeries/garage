package com.sss.garage.model.acclap;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccLapRepository extends JpaRepository<AccLap, Long> {
    @Query("SELECT l FROM AccLap l WHERE (l.serverName=:serverName OR :serverName IS NULL) " +
                                    "AND (l.trackName=:trackName OR :trackName IS NULL)" +
                                    "AND (l.sessionType=:sessionType OR :sessionType IS NULL)" +
                                    "AND (l.isValidForBest = true)")
    Page<AccLap> findAllByParams(String sessionType, String trackName, String serverName, Pageable pageable);

    @Query("SELECT l FROM AccLap l WHERE (l.serverName=:serverName OR :serverName IS NULL) " +
                                    "AND (l.trackName=:trackName OR :trackName IS NULL)" +
                                    "AND (l.sessionType=:sessionType OR :sessionType IS NULL)" +
                                    "AND (l.isValidForBest = true)")
    List<AccLap> findAllByParams(String sessionType, String trackName, String serverName);
}
