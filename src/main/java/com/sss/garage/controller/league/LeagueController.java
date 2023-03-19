package com.sss.garage.controller.league;

import static com.sss.garage.constants.WebConstants.DEFAULT_CURRENT_PAGE;
import static com.sss.garage.constants.WebConstants.DEFAULT_PAGE_SIZE;
import static com.sss.garage.constants.WebConstants.LEAGUE_ENDPOINT;

import java.util.List;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.dto.league.DetailedLeagueDTO;
import com.sss.garage.dto.league.LeagueDTO;
import com.sss.garage.dto.race.RaceDTO;
import com.sss.garage.facade.league.LeagueFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(LEAGUE_ENDPOINT)
@Tag(name = "Sss League")
public class LeagueController extends SssBaseController {

    private LeagueFacade leagueFacade;

    @GetMapping
    @Operation(operationId = "getLeagues", summary = "Get leagues", description =
            "Get all leagues... for now")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "List of LeagueDTO", content = @Content(schema = @Schema(implementation = LeagueDTO.class)))
    public List<LeagueDTO> getLeagues() {
        return leagueFacade.getAllLeagues().stream().map(r -> mapper.map(r, LeagueDTO.class)).toList();
    }

    @GetMapping(path = "/{leagueId}")
    @Operation(operationId = "getLeague", summary = "Get info about a specific league")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = DetailedLeagueDTO.class)))
    public DetailedLeagueDTO getLeague(@Parameter(description = "id of specified league") @PathVariable final Long leagueId) {
        return mapper.map(leagueFacade.getLeague(leagueId), DetailedLeagueDTO.class);
    }

    @Autowired
    public void setLeagueFacade(final LeagueFacade leagueFacade) {
        this.leagueFacade = leagueFacade;
    }
}
