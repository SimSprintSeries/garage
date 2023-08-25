package com.sss.garage.model.league;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {
    @Query("SELECT l FROM League l WHERE (l.platform=:platform OR :platform IS NULL) " +
            "AND (l.name=:name OR :name IS NULL)" +
            "AND (l.active=:active OR :active IS NULL)")
    Page<League> findAllByParams(String platform, String name, Boolean active, Pageable pageable);
}
