package com.sss.garage.dto.elo;

import com.sss.garage.dto.driver.SimpleDriverDTO;
import com.sss.garage.dto.game.GameDTO;

public class EloDTO {
    private Long id;
    private Integer value;
    private SimpleDriverDTO driver;
    private GameDTO game;

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

    public SimpleDriverDTO getDriver() {
        return driver;
    }

    public void setDriver(final SimpleDriverDTO driver) {
        this.driver = driver;
    }

    public GameDTO getGame() {
        return game;
    }

    public void setGame(final GameDTO game) {
        this.game = game;
    }
}
