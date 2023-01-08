package com.sss.garage.model.game;

import jakarta.persistence.Entity;

@Entity
public class GameFamily extends Game {

    private String gameName;

    public String getGameName() {
        return gameName;
    }
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
