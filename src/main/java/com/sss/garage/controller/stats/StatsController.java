package com.sss.garage.controller.stats;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.dto.stats.StatsDTO;
import com.sss.garage.facade.stats.StatsFacade;
import com.sss.garage.model.stats.Stats;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.sss.garage.constants.WebConstants.DEFAULT_CURRENT_PAGE;
import static com.sss.garage.constants.WebConstants.STATS_ENDPOINT;

@RestController
@RequestMapping(STATS_ENDPOINT)
@Tag(name = "Sss Stats")
public class StatsController extends SssBaseController {
    private StatsFacade statsFacade;

    @GetMapping
    @Operation(operationId = "getStats", summary = "Get stats for driver, league (optional) and game (optional)")
    @ResponseStatus(HttpStatus.OK)
    public StatsDTO getStats(@Parameter(description = "Driver ID to filter by") @RequestParam(value = "driverId") final String driverId,
                             @Parameter(description = "Optional league ID to filter by") @RequestParam(value = "leagueId", required = false) final String leagueId,
                             @Parameter(description = "Optional game ID to filter by") @RequestParam(value = "gameId", required = false) final String gameId,
                             @Parameter(description = "Optional split to filter by", schema = @Schema(description = "split", type = "String", allowableValues = "A,B,C")) @RequestParam(value = "split", required = false) final String split) {
        return mapper.map(statsFacade.getStats(driverId, leagueId, gameId, split), StatsDTO.class);
    }

    @Autowired
    public void setStatsFacade(StatsFacade statsFacade) {
        this.statsFacade = statsFacade;
    }
}
