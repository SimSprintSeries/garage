package com.sss.garage.model.racepointdictionary;

import java.util.Optional;

import com.sss.garage.model.racepointtype.RacePointType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RacePointDictionaryRepository extends JpaRepository<RacePointDictionary, Long> {
    Optional<RacePointDictionary> findByRacePointType(final RacePointType racePointType);
    boolean existsByRacePointType(final RacePointType racePointType);
}
