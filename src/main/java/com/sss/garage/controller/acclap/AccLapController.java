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

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "createLap", summary = "Create new lap")
    public void createLap(@RequestBody final AccLapDTO lapDTO) {

        lapFacade.createLap(mapper.map(lapDTO, AccLapData.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "deleteSession", summary = "Delete lap by ID")
    public void deleteLap(@PathVariable final Long id) {
        lapFacade.deleteLap(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "getLapsPaginated", summary = "Get list of all laps")
    public Page<AccLapDTO> getLapsPaginated(@Parameter(description = "The current result page requested") @RequestParam(value = "currentPage", defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
                                            @Parameter(description = "The number of results returned per page") @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
                                            @Parameter(description = "Sorting method applied to the returned results") @RequestParam(value = "sort", defaultValue = "id") final String sort,
                                            @Parameter(description = "Sorting direction", schema = @Schema(description = "sort", type = "String", allowableValues = "ASC,DESC")) @RequestParam(value = "sortDirection", defaultValue = "ASC") final String sortDirection,
                                            @Parameter(description = "Optional track name to filter by") @RequestParam(value = "trackName", required = false) final String trackName,
                                            @Parameter(description = "Optional session type to filter by") @RequestParam(value = "sessionType", required = false) final String sessionType,
                                            @Parameter(description = "Optional server/championship name to filter by") @RequestParam(value = "serverName", required = false) final String serverName) {
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sort));
        return this.lapFacade.getLapsPaginated(sessionType, trackName, serverName, pageable).map(l -> mapper.map(l, AccLapDTO.class));
    }

    @GetMapping("/fastest")
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "getFastestLapsForEveryDriver", summary = "Get fastest lap for every driver")
    public Page<AccLapDTO> getFastestLapsForEveryDriver(@Parameter(description = "The current result page requested") @RequestParam(value = "currentPage", defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
                                                        @Parameter(description = "The number of results returned per page") @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
                                                        @Parameter(description = "Sorting method applied to the returned results") @RequestParam(value = "sort", defaultValue = "laptime") final String sort,
                                                        @Parameter(description = "Sorting direction", schema = @Schema(description = "sort", type = "String", allowableValues = "ASC,DESC")) @RequestParam(value = "sortDirection", defaultValue = "ASC") final String sortDirection,
                                                        @Parameter(description = "Optional track name to filter by") @RequestParam(value = "trackName", required = false) final String trackName,
                                                        @Parameter(description = "Optional session type to filter by") @RequestParam(value = "sessionType", required = false) final String sessionType,
                                                        @Parameter(description = "Optional server/championship name to filter by") @RequestParam(value = "serverName", required = false) final String serverName) {
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sort));
        return this.lapFacade.getFastestLapsForEveryDriver(sessionType, trackName, serverName, pageable).map(l -> mapper.map(l, AccLapDTO.class));
    }

    @Autowired
    public void setLapFacade(final AccLapFacade lapFacade) {
        this.lapFacade = lapFacade;
    }
}
