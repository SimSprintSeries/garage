package com.sss.garage.model.elo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EloRepository extends JpaRepository<Elo, Long> {
}
