package com.sss.garage.controller.racepointdictionary;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.data.racepointdictionary.RacePointDictionaryData;
import com.sss.garage.dto.racepointdictionary.RacePointDictionaryDTO;
import com.sss.garage.facade.racepointdictionary.RacePointDictionaryFacade;
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
@RequestMapping(RACE_POINT_DICTIONARY_ENDPOINT)
@Tag(name = "Sss Race Point Dictionary")
public class RacePointDictionaryController extends SssBaseController {
    private RacePointDictionaryFacade racePointDictionaryFacade;

    @GetMapping
    @Operation(operationId = "getRacePointDictionaries", summary = "Get race point dictionaries", description = "Get all race point dictionaries containing point system for specific league")
    @ResponseStatus(HttpStatus.OK)
    public Page<RacePointDictionaryDTO> racePointDictionaries(@Parameter(description = "The current result page requested") @RequestParam(value = "currentPage", defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
                                                              @Parameter(description = "The number of results returned per page") @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
                                                              @Parameter(description = "Sorting method applied to the returned results") @RequestParam(value = "sort", defaultValue = "id") final String sort,
                                                              @Parameter(description = "Sorting direction", schema = @Schema(description = "sort", type = "String", allowableValues = "ASC,DESC")) @RequestParam(value = "sortDirection", defaultValue = "ASC") final String sortDirection) {
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sort));

        return racePointDictionaryFacade.getRacePointDictionaries(pageable).map(p -> mapper.map(p, RacePointDictionaryDTO.class));
    }

    @Autowired
    public void setRacePointDictionaryFacade(final RacePointDictionaryFacade racePointDictionaryFacade) {
        this.racePointDictionaryFacade = racePointDictionaryFacade;
    }
}
