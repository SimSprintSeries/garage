package com.sss.garage.controller.penalty;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.data.penalty.PenaltyData;
import com.sss.garage.dto.penalty.PenaltyDTO;
import com.sss.garage.facade.penalty.PenaltyFacade;
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

import java.util.List;

import static com.sss.garage.constants.WebConstants.*;

@RestController
@RequestMapping(PENALTY_ENDPOINT)
@Tag(name = "Sss Penalty")
public class PenaltyController extends SssBaseController {
    private PenaltyFacade penaltyFacade;

    @GetMapping
    @Operation(operationId = "getPenalties", summary = "Get list of all penalties")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "List of PenaltyDTO", content = @Content(schema = @Schema(implementation = PenaltyDTO.class)))
    public List<PenaltyDTO> getPenalties() {
        return mapAsList(this.penaltyFacade.getAllPenalties(), PenaltyDTO.class);
    }

    @GetMapping("/{id}")
    @Operation(operationId = "getPenalty", summary = "Get penalty information")
    @ResponseStatus(HttpStatus.OK)
    public PenaltyDTO getPenalty(@PathVariable final Long id) {
        PenaltyData gameData = penaltyFacade.getPenalty(id);

        return mapper.map(gameData, PenaltyDTO.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "createPenalty", summary = "Create new penalty")
    public void createPenalty(@RequestBody PenaltyDTO penaltyDTO) {
        penaltyFacade.createPenalty(mapper.map(penaltyDTO, PenaltyData.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "deletePenalty", summary = "Delete penalty by ID")
    public void deleteGame(@PathVariable final Long id) {
        penaltyFacade.deletePenalty(id);
    }

    @GetMapping("/paginated")
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "getPenaltiesPaginated", summary = "Get all penalties paginated")
    public Page<PenaltyDTO> getPenaltiesPaginated(@Parameter(description = "The current result page requested") @RequestParam(value = "currentPage", defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
                                                  @Parameter(description = "The number of results returned per page") @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
                                                  @Parameter(description = "Sorting method applied to the returned results") @RequestParam(value = "sort", defaultValue = "id") final String sort,
                                                  @Parameter(description = "Sorting direction", schema = @Schema(description = "sort", type = "String", allowableValues = "ASC,DESC")) @RequestParam(value = "sortDirection", defaultValue = "ASC") final String sortDirection,
                                                  @Parameter(description = "Optional checked flag to filter by - true returns checked reports, false returns unchecked, null returns all")
                                                      @RequestParam(value = "checked", required = false) final Boolean checked) {
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sort));

        return this.penaltyFacade.getPenaltiesPaginated(checked, pageable).map(p -> mapper.map(p, PenaltyDTO.class));
    }

    @Autowired
    public void setPenaltyFacade(final PenaltyFacade penaltyFacade) {
        this.penaltyFacade = penaltyFacade;
    }

}
