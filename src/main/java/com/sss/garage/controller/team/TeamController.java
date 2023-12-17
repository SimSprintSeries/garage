package com.sss.garage.controller.team;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.data.team.TeamData;
import com.sss.garage.dto.entry.EntryDTO;
import com.sss.garage.dto.team.TeamDTO;
import com.sss.garage.facade.team.TeamFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.sss.garage.constants.WebConstants.*;

@RestController
@RequestMapping(TEAM_ENDPOINT)
@Tag(name = "Sss Team")
public class TeamController extends SssBaseController {
    private TeamFacade teamFacade;

    @GetMapping
    @Operation(operationId = "getTeams", summary = "Get list of all teams")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "List of TeamDTO", content = @Content(schema = @Schema(implementation = TeamDTO.class)))
    public Page<TeamDTO> getTeams(@Parameter(description = "The current result page requested") @RequestParam(value = "currentPage", defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
                                  @Parameter(description = "The number of results returned per page") @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
                                  @Parameter(description = "Sorting method applied to the returned results") @RequestParam(value = "sort", defaultValue = "id") final String sort,
                                  @Parameter(description = "Sorting direction", schema = @Schema(description = "sort", type = "String", allowableValues = "ASC,DESC")) @RequestParam(value = "sortDirection", defaultValue = "ASC") final String sortDirection) {
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sort));

        return teamFacade.getAllTeams(pageable).map(t -> mapper.map(t, TeamDTO.class));
    }

    @GetMapping("/{id}")
    @Operation(operationId = "getTeam", summary = "Get team information")
    @ResponseStatus(HttpStatus.OK)
    public TeamDTO getTeam(@PathVariable final Long id) {
        TeamData teamData = teamFacade.getTeamById(id);

        return mapper.map(teamData, TeamDTO.class);
    }

    @PostMapping
    @Operation(operationId = "createTeam", summary = "Create new team")
    @ResponseStatus(HttpStatus.OK)
    public void createTeam(@RequestBody final TeamDTO teamDTO) {
        teamFacade.createTeam(mapper.map(teamDTO, TeamData.class));
    }

    @DeleteMapping("/{id}")
    @Operation(operationId = "deleteTeam", summary = "Delete team by ID")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTeam(@PathVariable final Long id) {
        teamFacade.deleteTeam(id);
    }

    @Autowired
    public void setTeamFacade(final TeamFacade teamFacade) {
        this.teamFacade = teamFacade;
    }
}
