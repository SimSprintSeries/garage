package com.sss.garage.controller.acclap;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.data.acclap.AccLapData;
import com.sss.garage.dto.acclap.AccLapDTO;
import com.sss.garage.facade.acclap.AccLapFacade;
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
@RequestMapping(ACC_LAP_ENDPOINT)
@Tag(name = "Sss Acc Lap")
public class AccLapController extends SssBaseController {
    private AccLapFacade lapFacade;

    @GetMapping("/{id}")
    @Operation(operationId = "getLap", summary = "Get lap information")
    @ResponseStatus(HttpStatus.OK)
    public AccLapDTO getLap(@PathVariable final Long id) {
        AccLapData lapData = lapFacade.getLap(id);

        return mapper.map(lapData, AccLapDTO.class);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "getFastestLapsForEveryDriver", summary = "Get fastest lap for every driver")
    public Page<AccLapDTO> getFastestLapsForEveryDriver(@Parameter(description = "The current result page requested") @RequestParam(value = "currentPage", defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
                                                        @Parameter(description = "The number of results returned per page") @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
                                                        @Parameter(description = "Sorting method applied to the returned results") @RequestParam(value = "sort", defaultValue = "laptime") String sort,
                                                        @Parameter(description = "Sorting direction", schema = @Schema(description = "sort", type = "String", allowableValues = "ASC,DESC")) @RequestParam(value = "sortDirection", defaultValue = "ASC") final String sortDirection,
                                                        @Parameter(description = "Optional track ID to filter by") @RequestParam(value = "trackId", required = false) final String trackId,
                                                        @Parameter(description = "Optional session type to filter by") @RequestParam(value = "sessionType", required = false) final String sessionType,
                                                        @Parameter(description = "Optional league ID to filter by") @RequestParam(value = "leagueId", required = false) final String leagueId,
                                                        @Parameter(description = "Optional class name to filter by") @RequestParam(value = "className", required = false) final String className) {
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sort));
        return this.lapFacade.getFastestLapsForEveryDriver(sessionType, trackId, leagueId, className, pageable).map(l -> mapper.map(l, AccLapDTO.class));
    }

    @Autowired
    public void setLapFacade(final AccLapFacade lapFacade) {
        this.lapFacade = lapFacade;
    }
}
