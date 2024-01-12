package com.sss.garage.controller.split;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.data.split.SplitData;
import com.sss.garage.dto.split.SplitDTO;
import com.sss.garage.facade.split.SplitFacade;
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
@RequestMapping(SPLIT_ENDPOINT)
@Tag(name = "Sss Split")
public class SplitController extends SssBaseController {

    private SplitFacade splitFacade;

    @GetMapping("/{id}")
    @Operation(operationId = "getSplit", summary = "Get SplitController")
    @ResponseStatus(HttpStatus.OK)
    public SplitDTO getSplit(@PathVariable final Long id) {
        SplitData splitData = splitFacade.getSplit(id);

        return mapper.map(splitData, SplitDTO.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "createSplit", summary = "Create new SplitController")
    public void createSplit(@RequestBody SplitDTO splitDTO) {
        splitFacade.createSplit(mapper.map(splitDTO, SplitData.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "deleteSplit", summary = "Delete SplitController by ID")
    public void deleteSplit(@PathVariable final Long id) {
        splitFacade.deleteSplit(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "getSplitsPaginated", summary = "Get list of all splits")
    public Page<SplitDTO> getSplitsPaginated(@Parameter(description = "The current result page requested") @RequestParam(value = "currentPage", defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
                                            @Parameter(description = "The number of results returned per page") @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
                                            @Parameter(description = "Sorting method applied to the returned results") @RequestParam(value = "sort", defaultValue = "id") final String sort,
                                            @Parameter(description = "Sorting direction", schema = @Schema(description = "sort", type = "String", allowableValues = "ASC,DESC")) @RequestParam(value = "sortDirection", defaultValue = "ASC") final String sortDirection) {
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sort));
        return this.splitFacade.getSplitsPaginated(pageable).map(s -> mapper.map(s, SplitDTO.class));
    }

    @Autowired
    public void setSplitFacade(final SplitFacade splitFacade) {
        this.splitFacade = splitFacade;
    }
}
