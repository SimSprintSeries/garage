package com.sss.garage.controller.elo;

import static com.sss.garage.constants.WebConstants.DEFAULT_CURRENT_PAGE;
import static com.sss.garage.constants.WebConstants.DEFAULT_PAGE_SIZE;
import static com.sss.garage.constants.WebConstants.ELO_ENDPOINT;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.dto.elo.EloDTO;
import com.sss.garage.facade.elo.EloFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
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
@RequestMapping(ELO_ENDPOINT)
@Tag(name = "Sss Elo")
public class EloController extends SssBaseController {

    private EloFacade eloFacade;

    @GetMapping
    @Operation(operationId = "getElos", summary = "Get elo values sorted by Elo value", description = "Get all elo values with sorting option. Optionally you can filter by game/gameFamily. If not, all elo is returned")
    @ResponseStatus(HttpStatus.OK)
    public Page<EloDTO> getElos(@Parameter(description = "The current result page requested") @RequestParam(value = "currentPage", defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
                                @Parameter(description = "The number of results returned per page") @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
                                @Parameter(description = "Sorting method applied to the returned results") @RequestParam(value = "sort", defaultValue = "value") final String sort,
                                @Parameter(description = "Sorting direction", schema = @Schema(description = "sort", type = "String", allowableValues = "ASC,DESC")) @RequestParam(value = "sortDirection", defaultValue = "DESC") final String sortDirection,
                                @Parameter(description = "Optional game id to filter by") @RequestParam(value = "gameId", required = false) final String gameId) {
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sort));

        return this.eloFacade.getElosPaginated(gameId, pageable).map(e -> mapper.map(e, EloDTO.class));
    }

    @Autowired
    public void setEloFacade(final EloFacade eloFacade) {
        this.eloFacade = eloFacade;
    }
}
