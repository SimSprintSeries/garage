package com.sss.garage.model.acclap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccLapRepository extends JpaRepository<AccLap, Long> {
}
