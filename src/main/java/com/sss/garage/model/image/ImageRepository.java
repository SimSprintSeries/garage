package com.sss.garage.model.image;

import com.sss.garage.model.league.League;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByLeague(League league);
}
