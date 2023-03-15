package com.sss.garage.controller.elo;

import static com.sss.garage.constants.WebConstants.DRIVER_ELO_ENDPOINT;

import java.util.Optional;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.data.elo.EloData;
import com.sss.garage.dto.elo.EloDTO;
import com.sss.garage.facade.elo.EloFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(DRIVER_ELO_ENDPOINT)
@Tag(name = "Sss Driver Elo")
public class DriverEloController extends SssBaseController {

    private EloFacade eloFacade;

    @GetMapping("/game/{game}")
    @Operation(summary = "Get driver's elo by game id")
    @ApiResponse(responseCode = "204", description = "No elo found for driver")
    public EloDTO getElo(HttpServletResponse response,
                         @Parameter(description = "id of driver") @PathVariable final String driver,
                         @Parameter(description = "id of game") @PathVariable final String game) {
        final Optional<EloData> maybeElo = this.eloFacade.getElo(driver, game);
        if(maybeElo.isEmpty()) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return null;
        }

        return mapper.map(maybeElo.get(), EloDTO.class);
    }

    @Autowired
    public void setEloFacade(final EloFacade eloFacade) {
        this.eloFacade = eloFacade;
    }
}
