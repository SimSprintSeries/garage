package com.sss.garage.model.track;

import com.sss.garage.model.acclap.AccLap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {
    @Query("SELECT t FROM Track t WHERE (t.name=:name OR :name IS NULL) " +
            "AND (t.country=:country OR :country IS NULL)")
    Page<Track> findAllByParams(String name, String country, Pageable pageable);
}
