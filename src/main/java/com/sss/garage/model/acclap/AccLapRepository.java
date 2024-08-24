package com.sss.garage.model.acclap;

import com.sss.garage.model.track.Track;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccLapRepository extends JpaRepository<AccLap, Long> {
    @Query("SELECT l FROM AccLap l WHERE (l.serverName=:serverName OR :serverName IS NULL) " +
                                    "AND (l.track=:track OR :track IS NULL)" +
                                    "AND (l.sessionType=:sessionType OR :sessionType IS NULL)" +
                                    "AND (l.isValidForBest = true)")
    List<AccLap> findAllByParams(String sessionType, Track track, String serverName);
}
