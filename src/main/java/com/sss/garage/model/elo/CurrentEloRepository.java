package com.sss.garage.model.elo;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.game.Game;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository to retrieve only entities of implicit type Elo
 */
@Repository
public interface CurrentEloRepository extends JpaRepository<Elo, Long> {
    @Query("SELECT e FROM Elo e WHERE TYPE(e)=Elo AND e.game=:game AND e.driver=:driver")
    Optional<Elo> findByGameAndDriver(Game game, Driver driver);

    @Query("SELECT e FROM Elo e WHERE TYPE(e)=Elo AND e.game=:game")
    List<Elo> findByGame(Game game, Sort sort);

    @Query("SELECT e FROM Elo e WHERE TYPE(e)=Elo AND e.driver=:driver")
    List<Elo> findByDriver(Driver driver);

    @NotNull
    @Override
    @Query("SELECT e FROM Elo e WHERE TYPE(e)=Elo")
    Page<Elo> findAll(@NotNull Pageable pageable);

    @Query("SELECT e FROM Elo e WHERE TYPE(e)=Elo and e.game=:game")
    Page<Elo> findAllByGame(Game game, Pageable pageable);
}

