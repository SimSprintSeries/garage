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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(DRIVER_ELO_ENDPOINT)
@Api(tags = "Sss Driver Elo")
public class DriverEloController extends SssBaseController {

    private EloFacade eloFacade;

    @GetMapping("/game/{game}")
    public EloDTO getElo(HttpServletResponse response,
                         @ApiParam(value = "id of driver") @PathVariable final String driver,
                         @ApiParam(value = "id of game") @PathVariable final String game) {
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
