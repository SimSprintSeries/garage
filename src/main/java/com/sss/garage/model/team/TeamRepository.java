package com.sss.garage.model.team;

import com.sss.garage.model.league.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("SELECT t FROM RaceResult rr LEFT JOIN Team t ON rr.team = t LEFT JOIN Race r ON rr.race = r LEFT JOIN Event e ON r.event = e " +
            "WHERE e.league=:league " +
            "AND t.name NOT LIKE 'Rezerwa' AND LENGTH(t.name) > 1")
    List<Team> findTeamsByLeague(League league);
}
