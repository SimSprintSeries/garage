package com.sss.garage.model.race;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {
    List<Race> findByIncludedInElo(final Boolean includedInElo, Sort sort);
    List<Race> findByStartDateGreaterThanEqual(Date date, Sort sort);
}
