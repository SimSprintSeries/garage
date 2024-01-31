package com.sss.garage.controller.entry;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.data.entry.EntryData;
import com.sss.garage.dto.entry.EntryDTO;
import com.sss.garage.dto.game.GameDTO;
import com.sss.garage.facade.entry.EntryFacade;
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
@RequestMapping(ENTRY_ENDPOINT)
@Tag(name = "Sss Entry")
public class EntryController extends SssBaseController {
    private EntryFacade entryFacade;

    @GetMapping
    @Operation(operationId = "getEntries", summary = "Get list of all entries")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "List of EntryDTO", content = @Content(schema = @Schema(implementation = EntryDTO.class)))
    public Page<EntryDTO> getEntries(@Parameter(description = "The current result page requested") @RequestParam(value = "currentPage", defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
                                     @Parameter(description = "The number of results returned per page") @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
                                     @Parameter(description = "Sorting method applied to the returned results") @RequestParam(value = "sort", defaultValue = "id") final String sort,
                                     @Parameter(description = "Sorting direction", schema = @Schema(description = "sort", type = "String", allowableValues = "ASC,DESC")) @RequestParam(value = "sortDirection", defaultValue = "ASC") final String sortDirection,
                                     @Parameter(description = "Optional game ID to filter by") @RequestParam(value = "gameId", required = false) final String gameId) {
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sort));

        return entryFacade.getAllEntriesByGame(gameId, pageable).map(e -> mapper.map(e, EntryDTO.class));
    }

    @GetMapping("/{id}")
    @Operation(operationId = "getEntry", summary = "Get entry information")
    @ResponseStatus(HttpStatus.OK)
    public EntryDTO getEntry(@PathVariable final Long id) {
        EntryData entryData = entryFacade.getEntryById(id);

        return mapper.map(entryData, EntryDTO.class);
    }

    @PostMapping
    @Operation(operationId = "createEntry", summary = "Create new entry")
    @ResponseStatus(HttpStatus.OK)
    public void createEntry(@RequestBody final EntryDTO entryDTO) {
        entryFacade.createEntry(mapper.map(entryDTO, EntryData.class));
    }

    @DeleteMapping("/{id}")
    @Operation(operationId = "deleteEntry", summary = "Delete entry by ID")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEntry(@PathVariable final Long id) {
        entryFacade.deleteEntry(id);
    }

    @Autowired
    public void setEntryFacade(final EntryFacade entryFacade) {
        this.entryFacade = entryFacade;
    }
}
