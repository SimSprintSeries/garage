package com.sss.garage.controller.league;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.dto.league.LeagueDTO;
import com.sss.garage.facade.league.LeagueFacade;
import com.sss.garage.model.league.League;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sss.garage.constants.WebConstants.LEAGUE_ENDPOINT;

@RestController
@RequestMapping(LEAGUE_ENDPOINT)
@Tag(name = "Sss League")
public class LeagueController extends SssBaseController {

    private LeagueFacade leagueFacade;

    @GetMapping
    @Operation(operationId = "getAllLeagues", summary = "Get list of all leagues")
    @ResponseStatus(HttpStatus.OK)
    public List<LeagueDTO> getLeagues() {
        return mapAsList(this.leagueFacade.getAllLeagues(), LeagueDTO.class);
    }

    @GetMapping("/{id}")
    @Operation(operationId = "getLeague", summary = "Get league information")
    @ResponseStatus(HttpStatus.OK)
    public LeagueDTO getLeague(@PathVariable final Long id) {
        League league = leagueFacade.getLeague(id);

        return mapper.map(league, LeagueDTO.class);
    }

    @Autowired
    public void setLeagueFacade(final LeagueFacade leagueFacade) {
        this.leagueFacade = leagueFacade;
    }
}
