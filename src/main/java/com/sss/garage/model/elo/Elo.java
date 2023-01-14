package com.sss.garage.model.elo;

import jakarta.persistence.*;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.game.Game;
import org.jetbrains.annotations.NotNull;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"driver_id", "game_id"})})
public class Elo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer eloValue;

    @NotNull
    @ManyToOne
    private Driver driver;

    @NotNull
    @ManyToOne
    private Game game;


    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Integer getEloValue() {
        return eloValue;
    }

    public void setEloValue(final Integer eloValue) {
        this.eloValue = eloValue;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(final Driver driver) {
        this.driver = driver;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(final Game game) {
        this.game = game;
    }
}
