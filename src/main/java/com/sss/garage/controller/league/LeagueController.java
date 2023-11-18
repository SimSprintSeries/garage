package com.sss.garage.controller.league;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.data.league.LeagueData;
import com.sss.garage.dto.league.LeagueDTO;
import com.sss.garage.facade.league.LeagueFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import java.util.List;

import com.sss.garage.dto.league.DetailedLeagueDTO;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import static com.sss.garage.constants.WebConstants.*;

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
        return leagueFacade.getAllLeagues().stream().map(l -> mapper.map(l, LeagueDTO.class)).toList();
    }

    @GetMapping(path = "/{leagueId}")
    @Operation(operationId = "getLeague", summary = "Get info about a specific league")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = DetailedLeagueDTO.class)))
    public DetailedLeagueDTO getLeague(@Parameter(description = "id of specified league") @PathVariable final Long leagueId) {
        return mapper.map(leagueFacade.getLeague(leagueId), DetailedLeagueDTO.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "createLeague", summary = "Create new league")
    public void createLeague(@RequestBody LeagueDTO leagueDTO) {
        leagueFacade.createLeague(mapper.map(leagueDTO, LeagueData.class));
    }

    @GetMapping("/paginated")
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "getLeaguesPaginated", summary = "Get all leagues paginated")
    public Page<LeagueDTO> getLeaguesPaginated(@Parameter(description = "The current result page requested") @RequestParam(value = "currentPage", defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
                                               @Parameter(description = "The number of results returned per page") @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
                                               @Parameter(description = "Sorting method applied to the returned results") @RequestParam(value = "sort", defaultValue = "startDate") final String sort,
                                               @Parameter(description = "Sorting direction", schema = @Schema(description = "sort", type = "String", allowableValues = "ASC,DESC")) @RequestParam(value = "sortDirection", defaultValue = "DESC") final String sortDirection,
                                               @Parameter(description = "Optional track name to filter by") @RequestParam(value = "platform", required = false) final String platform,
                                               @Parameter(description = "Optional track name to filter by") @RequestParam(value = "name", required = false) final String name,
                                               @Parameter(description = "Optional active flag to filter by - true returns active leagues, false returns non-active, null returns all")
                                                   @RequestParam(value = "active", required = false) final Boolean active) {
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sort));
        return this.leagueFacade.getLeaguesPaginated(platform, name, active, pageable).map(l -> mapper.map(l, LeagueDTO.class));
    }

    @Autowired
    public void setLeagueFacade(final LeagueFacade leagueFacade) {
        this.leagueFacade = leagueFacade;
    }
}
