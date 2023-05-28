package com.sss.garage.controller.game;

import static com.sss.garage.constants.WebConstants.GAME_ENDPOINT;

import java.util.List;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.data.game.GameData;
import com.sss.garage.dto.game.GameDTO;
import com.sss.garage.facade.game.GameFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(GAME_ENDPOINT)
@Tag(name = "Sss Game")
public class GameController extends SssBaseController {

    private GameFacade gameFacade;

    @GetMapping
    @Operation(operationId = "getGames", summary = "Get list of allgames")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "List of GameDTO", content = @Content(schema = @Schema(implementation = GameDTO.class)))
    public List<GameDTO> getGames() {
        return mapAsList(this.gameFacade.getAllGames(), GameDTO.class);
    }

    @GetMapping("/{id}")
    @Operation(operationId = "getGame", summary = "Get game information")
    @ResponseStatus(HttpStatus.OK)
    public GameDTO getGame(@PathVariable final Long id) {
        GameData gameData = gameFacade.getGame(id);

        return mapper.map(gameData, GameDTO.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "createGame", summary = "Create new game")
    public void createGame(@RequestBody GameDTO gameDTO) {
        gameFacade.createGame(mapper.map(gameDTO, GameData.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "deleteGame", summary = "Delete game by ID")
    public void deleteGame(@PathVariable final Long id) {
        gameFacade.deleteGame(id);
    }

    @Autowired
    public void setGameFacade(final GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }
}
