package com.sss.garage.dto.game;

public class GameDTO {
    private Integer id;
    private String name;
    private GameDTO gameFamily;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public GameDTO getGameFamily() {
        return gameFamily;
    }

    public void setGameFamily(final GameDTO gameFamily) {
        this.gameFamily = gameFamily;
    }
}
