package com.sss.garage.data.elo;

import com.sss.garage.data.driver.DriverData;
import com.sss.garage.data.game.GameData;

public class EloData {
    private Long id;
    private Integer value;
    private DriverData driver;
    private GameData game;

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

    public DriverData getDriver() {
        return driver;
    }

    public void setDriver(final DriverData driver) {
        this.driver = driver;
    }

    public GameData getGame() {
        return game;
    }

    public void setGame(final GameData game) {
        this.game = game;
    }
}
