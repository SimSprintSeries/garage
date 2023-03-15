package com.sss.garage.controller.elo;

import static com.sss.garage.constants.WebConstants.ELO_CALCULATION_ENDPOINT;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.facade.elo.EloFacade;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ELO_CALCULATION_ENDPOINT)
@Tag(name = "Sss Elo Calculation")
public class EloCalculationController extends SssBaseController {

    private EloFacade eloFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "calculateElo", summary = "Calculate every elo for every driver", description = "Deletes all current elos and calculates them anew")
    public void calculateElo() {
        this.eloFacade.calculateElo();
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "updateElo", summary = "Calculate elo for not calculated races")
    public void updateElo() {
        this.eloFacade.updateElo();
    }

    @PostMapping("/race/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "recalculateElo", summary = "Recalculate elo since race provided", description = "WARNING: TODO: For now we're not checking if elo has or has not been calculated for that race already. Beware!")
    public void recalculateElo(@Parameter(description = "id of race to recalculate from", required = true) @PathVariable final Long id) {
        this.eloFacade.updateEloSince(id);
    }

    @Autowired
    public void setEloFacade(final EloFacade eloFacade) {
        this.eloFacade = eloFacade;
    }
}
