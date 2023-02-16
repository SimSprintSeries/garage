package com.sss.garage.data.game;

public class GameData {
    private Long id;
    private String name;
    private GameData gameFamily;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public GameData getGameFamily() {
        return gameFamily;
    }

    public void setGameFamily(final GameData gameFamily) {
        this.gameFamily = gameFamily;
    }
}
