package com.sss.garage.controller.presence;

import com.sss.garage.facade.presence.PresenceFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.sss.garage.constants.WebConstants.PRESENCE_ENDPOINT;

@RestController
@RequestMapping(PRESENCE_ENDPOINT)
@Tag(name = "Sss Presence")
public class PresenceController {
    private PresenceFacade presenceFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "setPresenceForDriverAndEvent", summary = "Set presence for driver at specific event")
    public void setPresenceForDriverAndEvent(@Parameter(description = "Event ID") @RequestParam(value = "eventId") final String eventId,
                                             @Parameter(description = "Driver ID") @RequestParam(value = "driverId") final String driverId,
                                             @Parameter(description = "Presence") @RequestParam(value = "isPresent") final Boolean isPresent) {
        presenceFacade.setPresenceForDriverAndEvent(isPresent, eventId, driverId);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "deletePresenceForDriverAndEvent", summary = "Delete presence for driver at specific event")
    public void deletePresenceForDriverAndEvent(@Parameter(description = "Driver ID") @RequestParam(value = "driverId") final String driverId,
                                                @Parameter(description = "Event ID") @RequestParam(value = "eventId") final String eventId) {
        presenceFacade.deleteByDriverAndRace(driverId, eventId);
    }

    @Autowired
    public void setPresenceFacade(final PresenceFacade presenceFacade) {
        this.presenceFacade = presenceFacade;
    }
}
