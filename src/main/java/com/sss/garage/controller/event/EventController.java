package com.sss.garage.controller.event;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.data.event.EventData;
import com.sss.garage.dto.event.EventDTO;
import com.sss.garage.facade.event.EventFacade;
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
@RequestMapping(EVENT_ENDPOINT)
@Tag(name = "Sss Event")
public class EventController extends SssBaseController {
    private EventFacade eventFacade;

    @GetMapping
    @Operation(operationId = "getAllEvents", summary = "Get list of all events")
    @ResponseStatus(HttpStatus.OK)
    public Page<EventDTO> getAllEvents(@Parameter(description = "The current result page requested") @RequestParam(value = "currentPage", defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
                                       @Parameter(description = "The number of results returned per page") @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
                                       @Parameter(description = "Sorting method applied to the returned results") @RequestParam(value = "sort", defaultValue = "id") final String sort,
                                       @Parameter(description = "Sorting direction", schema = @Schema(description = "sort", type = "String", allowableValues = "ASC,DESC")) @RequestParam(value = "sortDirection", defaultValue = "ASC") final String sortDirection,
                                       @Parameter(description = "Optional league ID to filter by") @RequestParam(value = "leagueId", required = false) final String leagueId,
                                       @Parameter(description = "Optional track ID to filter by") @RequestParam(value = "trackId", required = false) final String trackId) {
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sort));
        return this.eventFacade.getAllEvents(leagueId, trackId, pageable).map(e -> mapper.map(e, EventDTO.class));
    }

    @GetMapping("/{id}")
    @Operation(operationId = "getEvent", summary = "Get event information")
    @ResponseStatus(HttpStatus.OK)
    public EventDTO getEvent(@PathVariable final Long id) {
        EventData eventData = eventFacade.getEvent(id);

        return mapper.map(eventData, EventDTO.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "createEvent", summary = "Create new event")
    public void createEvent(@RequestBody EventDTO eventDTO) {
        eventFacade.createEvent(mapper.map(eventDTO, EventData.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "deleteEvent", summary = "Delete event by ID")
    public void deleteEvent(@PathVariable final Long id) {
        eventFacade.deleteEvent(id);
    }

    @Autowired
    public void setEventFacade(final EventFacade eventFacade) {
        this.eventFacade = eventFacade;
    }
}
