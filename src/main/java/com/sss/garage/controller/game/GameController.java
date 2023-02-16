package com.sss.garage.controller.game;

import static com.sss.garage.constants.WebConstants.GAME_ENDPOINT;

import java.util.List;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.dto.game.GameDTO;
import com.sss.garage.facade.game.GameFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(GAME_ENDPOINT)
@Api(tags = "Sss Game")
public class GameController extends SssBaseController {

    private GameFacade gameFacade;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GameDTO> getGames() {
        return this.gameFacade.getAllGames().stream()
                .map(g -> mapper.map(g, GameDTO.class))
                .toList();
    }

    @Autowired
    public void setGameFacade(final GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }
}
