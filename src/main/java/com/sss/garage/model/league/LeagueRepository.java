package com.sss.garage.model.league;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {
}
