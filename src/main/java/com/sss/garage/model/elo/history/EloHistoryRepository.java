package com.sss.garage.model.elo.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EloHistoryRepository extends JpaRepository<EloHistory, Long> {
}
