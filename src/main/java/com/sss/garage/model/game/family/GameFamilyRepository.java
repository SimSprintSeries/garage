package com.sss.garage.model.game.family;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameFamilyRepository extends JpaRepository<GameFamily, Integer> {

}
