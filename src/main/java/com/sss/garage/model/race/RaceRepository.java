package com.sss.garage.model.race;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {
    List<Race> findAllByIncludedInElo(final Boolean includedInElo, final Sort sort);
    List<Race> findAllByStartDateGreaterThanEqual(final Date date, final Sort sort);
}
