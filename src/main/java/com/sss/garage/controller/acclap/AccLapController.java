package com.sss.garage.controller.acclap;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.data.acclap.AccLapData;
import com.sss.garage.dto.acclap.AccLapDTO;
import com.sss.garage.facade.acclap.AccLapFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sss.garage.constants.WebConstants.ACC_LAP_ENDPOINT;

@RestController
@RequestMapping(ACC_LAP_ENDPOINT)
@Tag(name = "Sss Acc Lap")
public class AccLapController extends SssBaseController {
    private AccLapFacade lapFacade;

    @GetMapping
    @Operation(operationId = "getAllLaps", summary = "Get list of all laps")
    @ResponseStatus(HttpStatus.OK)
    public List<AccLapDTO> getAllLaps() {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return mapAsList(lapFacade.getAllLaps(), AccLapDTO.class);
    }

    @GetMapping("/{id}")
    @Operation(operationId = "getLap", summary = "Get lap information")
    @ResponseStatus(HttpStatus.OK)
    public AccLapDTO getLap(@PathVariable final Long id) {
        AccLapData lapData = lapFacade.getLap(id);

        return mapper.map(lapData, AccLapDTO.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "createLap", summary = "Create new lap")
    public void createLap(@RequestBody final AccLapDTO lapDTO) {

        lapFacade.createLap(mapper.map(lapDTO, AccLapData.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "deleteSession", summary = "Delete lap by ID")
    public void deleteLap(@PathVariable final Long id) {
        lapFacade.deleteLap(id);
    }

    @Autowired
    public void setLapFacade(final AccLapFacade lapFacade) {
        this.lapFacade = lapFacade;
    }
}
