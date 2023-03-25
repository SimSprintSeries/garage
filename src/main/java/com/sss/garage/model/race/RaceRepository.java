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
    List<Race> findAllByPointScoring(final Boolean pointScoring, final Sort sort);
    List<Race> findAllByPointScoringAndIncludedInElo(final Boolean pointScoring, final Boolean includedInElo, final Sort sort);
    List<Race> findAllByStartDateGreaterThanEqual(final Date date, final Sort sort);
    Page<Race> findAllByDatePlaceholder(final Boolean datePlaceholder, final Pageable pageable);
    Page<Race> findAllByDatePlaceholderAndStartDateGreaterThan(final Boolean datePlaceholder, final Date date, final Pageable pageable);
    Page<Race> findAllByDatePlaceholderAndStartDateLessThanEqual(final Boolean datePlaceholder, final Date date, final Pageable pageable);
}
