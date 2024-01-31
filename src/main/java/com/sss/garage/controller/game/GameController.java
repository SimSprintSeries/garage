package com.sss.garage.controller.game;

import java.util.List;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.data.game.GameData;
import com.sss.garage.dto.game.GameDTO;
import com.sss.garage.facade.game.GameFacade;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import static com.sss.garage.constants.WebConstants.*;

@RestController
@RequestMapping(GAME_ENDPOINT)
@Tag(name = "Sss Game")
public class GameController extends SssBaseController {

    private GameFacade gameFacade;

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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "getGamesPaginated", summary = "Get list of all games")
    public Page<GameDTO> getGamesPaginated(@Parameter(description = "The current result page requested") @RequestParam(value = "currentPage", defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
                                            @Parameter(description = "The number of results returned per page") @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
                                            @Parameter(description = "Sorting method applied to the returned results") @RequestParam(value = "sort", defaultValue = "id") final String sort,
                                            @Parameter(description = "Sorting direction", schema = @Schema(description = "sort", type = "String", allowableValues = "ASC,DESC")) @RequestParam(value = "sortDirection", defaultValue = "ASC") final String sortDirection) {
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sort));
        return this.gameFacade.getGamesPaginated(pageable).map(g -> mapper.map(g, GameDTO.class));
    }

    @Autowired
    public void setGameFacade(final GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }
}
