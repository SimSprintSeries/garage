package com.sss.garage.model.elo;

import jakarta.persistence.*;

import com.sss.garage.model.driver.Driver;
import com.sss.garage.model.game.Game;
import org.jetbrains.annotations.NotNull;

@Entity
public class Elo {

    private static final Integer DEFAULT_ELO_VALUE = 1500;

    public Elo() {

    }

    public Elo(Driver driver, Game game) {
        this.value = DEFAULT_ELO_VALUE;
        this.driver = driver;
        this.game = game;
    }

    public Elo(final Elo elo) {
        this.value = elo.getValue();
        this.driver = elo.getDriver();
        this.game = elo.getGame();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer value;

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

    public Integer getValue() {
        return value;
    }

    public void setValue(final Integer value) {
        this.value = value;
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
