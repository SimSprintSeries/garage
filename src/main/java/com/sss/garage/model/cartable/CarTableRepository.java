package com.sss.garage.model.cartable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarTableRepository extends JpaRepository<CarTable, Integer> {
    Optional<CarTable> findById(final Integer id);
}
