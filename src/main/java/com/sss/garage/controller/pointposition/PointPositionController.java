package com.sss.garage.controller.pointposition;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.data.pointposition.PointPositionData;
import com.sss.garage.dto.pointposition.PointPositionDTO;
import com.sss.garage.facade.pointposition.PointPositionFacade;
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
@RequestMapping(POINT_POSITION_ENDPOINT)
@Tag(name = "Sss Point Position")
public class PointPositionController extends SssBaseController {
    private PointPositionFacade pointPositionFacade;

    @GetMapping
    @Operation(operationId = "getAllPointPositions", summary = "Get point positions", description = "Get all point positions")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "List of PointPositionDTO", content = @Content(schema = @Schema(implementation = PointPositionDTO.class)))
    public Page<PointPositionDTO> getAllPointPositions(@Parameter(description = "The current result page requested") @RequestParam(value = "currentPage", defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
                                                       @Parameter(description = "The number of results returned per page") @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
                                                       @Parameter(description = "Sorting method applied to the returned results") @RequestParam(value = "sort", defaultValue = "id") final String sort,
                                                       @Parameter(description = "Sorting direction", schema = @Schema(description = "sort", type = "String", allowableValues = "ASC,DESC")) @RequestParam(value = "sortDirection", defaultValue = "DESC") final String sortDirection,
                                                       @Parameter(description = "Optional point category ID to filter by") @RequestParam(value = "categoryId", required = false) final String categoryId) {
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sort));
        return this.pointPositionFacade.getAllPointPositions(categoryId, pageable).map(p -> mapper.map(p, PointPositionDTO.class));
    }

    @GetMapping(path = "/{id}")
    @Operation(operationId = "getPointPosition", summary = "Get info about a specific point position")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = PointPositionDTO.class)))
    public PointPositionDTO getPointPosition(@PathVariable final Long id) {
        return mapper.map(pointPositionFacade.getPointPosition(id), PointPositionDTO.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "createPointPosition", summary = "Create new point position")
    public void createPointPosition(@RequestBody final PointPositionDTO pointPositionDTO) {
        pointPositionFacade.createPointPosition(mapper.map(pointPositionDTO, PointPositionData.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "deletePointPosition", summary = "Delete point position by ID")
    public void deletePointPosition(final Long id) {
        pointPositionFacade.deletePointPosition(id);
    }

    @Autowired
    public void setPointPositionFacade(PointPositionFacade pointPositionFacade) {
        this.pointPositionFacade = pointPositionFacade;
    }
}
