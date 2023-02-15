package com.sss.garage.controller.elo;

import static com.sss.garage.constants.WebConstants.ELO_CALCULATION_ENDPOINT;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.facade.elo.EloFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
@Api(tags = "Sss Elo")
public class EloCalculationController extends SssBaseController {

    private EloFacade eloFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(nickname = "calculateElo", value = "Calculate every elo for every driver", notes = "Deletes all current elos and calculates them anew")
    public void calculateElo() {
        this.eloFacade.calculateElo();
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(nickname = "updateElo", value = "Calculate elo for not calculated races")
    public void updateElo() {
        this.eloFacade.updateElo();
    }

    @PostMapping("/race/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(nickname = "recalculateElo", value = "Recalculate elo since race provided")
    public void recalculateElo(@ApiParam(value = "id of race to recalculate from", required = true) @PathVariable final Long id) {
        this.eloFacade.updateEloSince(id);
    }

    @Autowired
    public void setEloFacade(final EloFacade eloFacade) {
        this.eloFacade = eloFacade;
    }
}
