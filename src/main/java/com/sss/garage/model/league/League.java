package com.sss.garage.model.role;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class League {

    @Id
    private Long id;
    private String split;
    private String platform;
    private String game;


    public Long getId() {
        return id;
    }
    public void setId(final Long id) {
        this.id = id;
    }

    public String getSplit() {
        return split;
    }
    public void setSplit(final String split) {
        this.split = split;
    }

    public String getPlatform() {
        return platform;
    }
    public void setPlatform(final String platform) {
        this.platform = platform;
    }

    public String getGame() {
        return game;
    }
    public void setGame(final String game) {
        this.game = game;
    }
}
