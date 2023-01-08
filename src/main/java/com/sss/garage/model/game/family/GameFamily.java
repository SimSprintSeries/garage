package com.sss.garage.model.game.family;

import java.util.Set;

import com.sss.garage.model.game.Game;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class GameFamily extends Game {

    @OneToMany(mappedBy = "gameFamily")
    private Set<Game> games;

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(final Set<Game> games) {
        this.games = games;
    }
}
