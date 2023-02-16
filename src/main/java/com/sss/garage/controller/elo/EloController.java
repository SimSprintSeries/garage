package com.sss.garage.controller.elo;

import static com.sss.garage.constants.WebConstants.DEFAULT_CURRENT_PAGE;
import static com.sss.garage.constants.WebConstants.DEFAULT_PAGE_SIZE;
import static com.sss.garage.constants.WebConstants.ELO_ENDPOINT;

import java.util.Arrays;
import java.util.List;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.dto.elo.EloDTO;
import com.sss.garage.facade.elo.EloFacade;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(ELO_ENDPOINT)
@Api(tags = "Sss Elo")
public class EloController extends SssBaseController {

    private EloFacade eloFacade;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<EloDTO> getElos(@ApiParam(value = "The current result page requested") @RequestParam(value = "currentPage", defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
                                @ApiParam(value = "The number of results returned per page") @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
                                @ApiParam(value = "Sorting method applied to the returned results") @RequestParam(value = "sort", defaultValue = "value") final String sort,
                                @ApiParam(value = "Sorting direction", allowableValues = "ASC,DESC") @RequestParam(value = "sortDirection", defaultValue = "DESC") final String sortDirection,
                                @ApiParam(value = "Optional game id to filter by") @RequestParam(value = "gameId", required = false) final String gameId) {
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sort));

        return this.eloFacade.getElosPaginated(gameId, pageable).map(e -> mapper.map(e, EloDTO.class));
    }

    @Autowired
    public void setEloFacade(final EloFacade eloFacade) {
        this.eloFacade = eloFacade;
    }
}
