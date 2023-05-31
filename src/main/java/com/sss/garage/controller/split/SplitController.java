package com.sss.garage.controller.split;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.data.split.SplitData;
import com.sss.garage.dto.split.SplitDTO;
import com.sss.garage.facade.split.SplitFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sss.garage.constants.WebConstants.SPLIT_ENDPOINT;

@RestController
@RequestMapping(SPLIT_ENDPOINT)
@Tag(name = "Sss Split")
public class SplitController extends SssBaseController {

    private SplitFacade splitFacade;

    @GetMapping
    @Operation(operationId = "getAllSplits", summary = "Get list of all splits")
    @ResponseStatus(HttpStatus.OK)
    public List<SplitDTO> getAllSplits() {
        return mapAsList(splitFacade.getAllSplits(), SplitDTO.class);
    }

    @GetMapping("/{id}")
    @Operation(operationId = "getSplit", summary = "Get SplitController")
    @ResponseStatus(HttpStatus.OK)
    public SplitDTO getSplit(@PathVariable final Long id) {
        SplitData splitData = splitFacade.getSplit(id);

        return mapper.map(splitData, SplitDTO.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "createSplit", summary = "Create new SplitController")
    public void createSplit(@RequestBody SplitDTO splitDTO) {
        splitFacade.createSplit(mapper.map(splitDTO, SplitData.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "deleteSplit", summary = "Delete SplitController by ID")
    public void deleteSplit(@PathVariable final Long id) {
        splitFacade.deleteSplit(id);
    }

    @Autowired
    public void setSplitFacade(SplitFacade splitFacade) {
        this.splitFacade = splitFacade;
    }
}
