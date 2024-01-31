package com.sss.garage.controller.classification;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.dto.classification.ClassificationDTO;
import com.sss.garage.facade.classification.ClassificationFacade;
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

import static com.sss.garage.constants.WebConstants.*;

@RestController
@RequestMapping(CLASSIFICATION_ENDPOINT)
@Tag(name = "Sss Classification")
public class ClassificationController extends SssBaseController {
    private ClassificationFacade classificationFacade;

    @GetMapping
    @Operation(operationId = "getClassification", summary = "Get driver classification for league")
    @ResponseStatus(HttpStatus.OK)
    public Page<ClassificationDTO> getClassification(@Parameter(description = "The current result page requested") @RequestParam(value = "currentPage", defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
                                                     @Parameter(description = "The number of results returned per page") @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
                                                     @Parameter(description = "Sorting method applied to the returned results") @RequestParam(value = "sort", defaultValue = "id") final String sort,
                                                     @Parameter(description = "Sorting direction", schema = @Schema(description = "sort", type = "String", allowableValues = "ASC,DESC")) @RequestParam(value = "sortDirection", defaultValue = "DESC") final String sortDirection,
                                                     @Parameter(description = "League ID to filter by") @RequestParam(value = "leagueId") final String leagueId) {
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sort));
        return this.classificationFacade.getClassification(leagueId, pageable).map(c -> mapper.map(c, ClassificationDTO.class));
    }

    @GetMapping("/team")
    @Operation(operationId = "getClassificationForTeams", summary = "Get team classification for league")
    @ResponseStatus(HttpStatus.OK)
    public Page<ClassificationDTO> getClassificationForTeams(@Parameter(description = "The current result page requested") @RequestParam(value = "currentPage", defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
                                                             @Parameter(description = "The number of results returned per page") @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
                                                             @Parameter(description = "Sorting method applied to the returned results") @RequestParam(value = "sort", defaultValue = "id") final String sort,
                                                             @Parameter(description = "Sorting direction", schema = @Schema(description = "sort", type = "String", allowableValues = "ASC,DESC")) @RequestParam(value = "sortDirection", defaultValue = "DESC") final String sortDirection,
                                                             @Parameter(description = "League ID to filter by") @RequestParam(value = "leagueId") final String leagueId) {
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sort));
        return this.classificationFacade.getClassificationForTeams(leagueId, pageable).map(c -> mapper.map(c, ClassificationDTO.class));
    }

    @Autowired
    public void setClassificationFacade(ClassificationFacade classificationFacade) {
        this.classificationFacade = classificationFacade;
    }
}
