package com.sss.garage.controller.raceresult;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.data.raceresult.RaceResultData;
import com.sss.garage.dto.raceresult.RaceResultDTO;
import com.sss.garage.facade.raceresult.RaceResultFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sss.garage.constants.WebConstants.*;

@RestController
@RequestMapping(RACE_RESULT_ENDPOINT)
@Tag(name = "Sss Race Result")
public class RaceResultController extends SssBaseController {

    private RaceResultFacade raceResultFacade;

    @GetMapping
    @Operation(operationId = "getAllRaceResults", summary = "Get list of all race results")
    @ResponseStatus(HttpStatus.OK)
    public List<RaceResultDTO> getAllRaceResults() {
        return mapAsList(raceResultFacade.getAllRaceResults(), RaceResultDTO.class);
    }

    @GetMapping("/{id}")
    @Operation(operationId = "getRaceResult", summary = "Get race result")
    @ResponseStatus(HttpStatus.OK)
    public RaceResultDTO getRaceResult(@PathVariable final Long id) {
        RaceResultData raceResultData = raceResultFacade.getRaceResult(id);

        return mapper.map(raceResultData, RaceResultDTO.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "createRaceResult", summary = "Create new race result")
    public void createRaceResult(@RequestBody RaceResultDTO raceResultDTO) {
        raceResultFacade.createRaceResult(mapper.map(raceResultDTO, RaceResultData.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "deleteRaceResult", summary = "Delete race result by ID")
    public void deleteRaceResult(@PathVariable final Long id) {
        raceResultFacade.deleteRaceResult(id);
    }

    @GetMapping("/paginated")
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "getRaceResultsPaginated", summary = "Get all race results paginated")
    public Page<RaceResultDTO> getRaceResultsPaginated(@Parameter(description = "The current result page requested") @RequestParam(value = "currentPage", defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
                                                       @Parameter(description = "The number of results returned per page") @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
                                                       @Parameter(description = "Sorting method applied to the returned results") @RequestParam(value = "sort", defaultValue = "id") final String sort,
                                                       @Parameter(description = "Sorting direction", schema = @Schema(description = "sort", type = "String", allowableValues = "ASC,DESC")) @RequestParam(value = "sortDirection", defaultValue = "ASC") final String sortDirection,
                                                       @Parameter(description = "Optional finish position to filter by") @RequestParam(value = "finishPosition", required = false) final String finishPosition,
                                                       @Parameter(description = "Optional pole position flag to filter by - true returns pole position holders for every race, false returns opposite, null returns all")
                                                           @RequestParam(value = "polePosition", required = false) final Boolean polePosition,
                                                       @Parameter(description = "Optional DNF flag to filter by - true returns drivers who didn't finish the race, false returns opposite, null returns all")
                                                           @RequestParam(value = "dnf", required = false) final Boolean dnf,
                                                       @Parameter(description = "Optional DSQ flag to filter by - true returns disqualified drivers, false returns opposite, null returns all")
                                                           @RequestParam(value = "dsq", required = false) final Boolean dsq,
                                                       @Parameter(description = "Optional fastest lap flag to filter by - true returns fastest lap holders for every race, false returns opposite, null returns all")
                                                           @RequestParam(value = "fastestLap", required = false) final Boolean fastestLap,
                                                       @Parameter(description = "Optional driver ID to filter by") @RequestParam(value = "driverId", required = false) final String driverId,
                                                       @Parameter(description = "Optional race ID to filter by") @RequestParam(value = "raceId", required = false) final String raceId) {
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sort));
        return this.raceResultFacade.getRaceResultsPaginated(finishPosition, polePosition, dnf, dsq, fastestLap, driverId, raceId, pageable).map(r -> mapper.map(r, RaceResultDTO.class));
    }

    @Autowired
    public void setRaceResultFacade(final RaceResultFacade raceResultFacade) {
        this.raceResultFacade = raceResultFacade;
    }
}
