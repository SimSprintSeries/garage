package com.sss.garage.controller.race;

import static com.sss.garage.constants.WebConstants.DEFAULT_CURRENT_PAGE;
import static com.sss.garage.constants.WebConstants.DEFAULT_PAGE_SIZE;
import static com.sss.garage.constants.WebConstants.RACE_ENDPOINT;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.data.race.RaceData;
import com.sss.garage.dto.race.RaceDTO;
import com.sss.garage.facade.race.RaceFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping(RACE_ENDPOINT)
@Tag(name = "Sss Race")
public class RaceController extends SssBaseController {

    private RaceFacade raceFacade;

    @GetMapping
    @Operation(operationId = "getRaces", summary = "Get races sorted by race date", description = "Get all races with sorting option. Optionally you can filter by completed flag, which indicates whether race has already completed or not.")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Page of RaceDTO", content = @Content(schema = @Schema(implementation = RaceDTO.class)))
    public Page<RaceDTO> getEvents(@Parameter(description = "The current result page requested") @RequestParam(value = "currentPage", defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
                                   @Parameter(description = "The number of results returned per page") @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
                                   @Parameter(description = "Sorting method applied to the returned results") @RequestParam(value = "sort", defaultValue = "startDate") final String sort,
                                   @Parameter(description = "Sorting direction", schema = @Schema(description = "sort", type = "String", allowableValues = "ASC,DESC")) @RequestParam(value = "sortDirection", defaultValue = "DESC") final String sortDirection,
                                   @Parameter(description = "Optional completed flag to filter by. True to get only completed events, false to get upcoming, null to get all")
                                                @RequestParam(value = "completed", required = false) final Boolean completed) {
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sort));

        return raceFacade.getRacesPaginated(completed, pageable).map(r -> mapper.map(r, RaceDTO.class));
    }

    @GetMapping("allRaces")
    @Operation(operationId = "getAllRaces", summary = "Get list of races")
    @ResponseStatus(HttpStatus.OK)
    public List<RaceDTO> getAllRaces() {
        return mapAsList(this.raceFacade.getAllRaces(), RaceDTO.class);
    }

    @GetMapping("/{id}")
    @Operation(operationId = "getRace", summary = "Get race information")
    @ResponseStatus(HttpStatus.OK)
    public RaceDTO getRace(@PathVariable final Long id) {
        RaceData raceData = raceFacade.getRace(id);

        return mapper.map(raceData, RaceDTO.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "createRace", summary = "Create new race")
    public void createRace(@RequestBody RaceDTO raceDTO) {
        raceFacade.createRace(mapper.map(raceDTO, RaceData.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "deleteRace", summary = "Delete race by ID")
    public void deleteRace(@PathVariable final Long id) {
        raceFacade.deleteRace(id);
    }

    @Autowired
    public void setRaceFacade(final RaceFacade raceFacade) {
        this.raceFacade = raceFacade;
    }
}
