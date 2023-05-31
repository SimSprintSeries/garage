package com.sss.garage.controller.raceresult;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.data.raceresult.RaceResultData;
import com.sss.garage.dto.raceresult.RaceResultDTO;
import com.sss.garage.facade.raceresult.RaceResultFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sss.garage.constants.WebConstants.RACE_RESULT_ENDPOINT;

@RestController
@RequestMapping(RACE_RESULT_ENDPOINT)
@Tag(name = "Sss Race Result")
public class RaceResultController extends SssBaseController {

    private RaceResultFacade raceResultFacade;

    @GetMapping
    @Operation(operationId = "getAllRaceResults", summary = "Get list of all race results")
    @ResponseStatus(HttpStatus.OK)
    public List<RaceResultDTO> getAllRaceResults() {
        return mapAsList(raceResultFacade.getAllRaceResults(), RaceResultDTO.class);
    }

    @GetMapping("/{id}")
    @Operation(operationId = "getRaceResult", summary = "Get race result")
    @ResponseStatus(HttpStatus.OK)
    public RaceResultDTO getRaceResult(@PathVariable final Long id) {
        RaceResultData raceResultData = raceResultFacade.getRaceResult(id);

        return mapper.map(raceResultData, RaceResultDTO.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "createRaceResult", summary = "Create new race result")
    public void createRaceResult(@RequestBody RaceResultDTO raceResultDTO) {
        raceResultFacade.createRaceResult(mapper.map(raceResultDTO, RaceResultData.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "deleteRaceResult", summary = "Delete race result by ID")
    public void deleteRaceResult(@PathVariable final Long id) {
        raceResultFacade.deleteRaceResult(id);
    }

    @Autowired
    public void setRaceResultFacade(final RaceResultFacade raceResultFacade) {
        this.raceResultFacade = raceResultFacade;
    }
}
