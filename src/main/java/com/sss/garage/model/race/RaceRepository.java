package com.sss.garage.model.race;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {
    List<Race> findAllByIncludedInElo(final Boolean includedInElo, final Sort sort);
    List<Race> findAllByStartDateGreaterThanEqual(final Date date, final Sort sort);
    Page<Race> findAllByParentRaceEventIsNull(final Pageable pageable);
    Page<Race> findAllByParentRaceEventIsNullAndStartDateGreaterThan(final Date date, final Pageable pageable);
    Page<Race> findAllByParentRaceEventIsNullAndStartDateLessThanEqual(final Date date, final Pageable pageable);
}
