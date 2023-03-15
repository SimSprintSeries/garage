package com.sss.garage.controller.race;

import static com.sss.garage.constants.WebConstants.DEFAULT_CURRENT_PAGE;
import static com.sss.garage.constants.WebConstants.DEFAULT_PAGE_SIZE;
import static com.sss.garage.constants.WebConstants.RACE_ENDPOINT;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.dto.race.RaceDTO;
import com.sss.garage.facade.race.RaceFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(RACE_ENDPOINT)
@Tag(name = "Sss Race")
public class RaceController extends SssBaseController {

    private RaceFacade raceFacade;

    @GetMapping
    @Operation(operationId = "getRaces", summary = "Get races sorted by race date", description = "Get all races with sorting option. Optionally you can filter by completed flag, which indicates whether race has already completed or not.")
    @ResponseStatus(HttpStatus.OK)
    public Page<RaceDTO> getEvents(@Parameter(description = "The current result page requested") @RequestParam(value = "currentPage", defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
                                   @Parameter(description = "The number of results returned per page") @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
                                   @Parameter(description = "Sorting method applied to the returned results") @RequestParam(value = "sort", defaultValue = "startDate") final String sort,
                                   @Parameter(description = "Sorting direction", schema = @Schema(description = "sort", type = "String", allowableValues = "ASC,DESC")) @RequestParam(value = "sortDirection", defaultValue = "DESC") final String sortDirection,
                                   @Parameter(description = "Optional completed flag to filter by. True to get only completed events, false to get upcoming, null to get all")
                                                @RequestParam(value = "completed", required = false) final Boolean completed) {
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sort));

        return raceFacade.getRacesPaginated(completed, pageable).map(r -> mapper.map(r, RaceDTO.class));
    }

    @Autowired
    public void setRaceFacade(final RaceFacade raceFacade) {
        this.raceFacade = raceFacade;
    }
}
