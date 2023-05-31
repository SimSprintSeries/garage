package com.sss.garage.controller.track;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.data.track.TrackData;
import com.sss.garage.dto.track.TrackDTO;
import com.sss.garage.facade.track.TrackFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sss.garage.constants.WebConstants.TRACK_ENDPOINT;

@RestController
@RequestMapping(TRACK_ENDPOINT)
@Tag(name = "Sss Track")
public class TrackController extends SssBaseController {

    private TrackFacade trackFacade;

    @GetMapping
    @Operation(operationId = "getAllTracks", summary = "Get list of all tracks")
    @ResponseStatus(HttpStatus.OK)
    public List<TrackDTO> getAllTracks() {
        return mapAsList(trackFacade.getAllTracks(), TrackDTO.class);
    }

    @GetMapping("/{id}")
    @Operation(operationId = "getTrack", summary = "Get track by ID")
    @ResponseStatus(HttpStatus.OK)
    public TrackDTO getTrack(@PathVariable final Long id) {
        TrackData trackData = trackFacade.getTrack(id);

        return mapper.map(trackData, TrackDTO.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "createTrack", summary = "Create new track")
    public void createTrack(@RequestBody TrackDTO trackDTO) {
        trackFacade.createTrack(mapper.map(trackDTO, TrackData.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "deleteTrack", summary = "Delete track by ID")
    public void deleteTrack(@PathVariable final Long id) {
        trackFacade.deleteTrack(id);
    }

    @Autowired
    public void setTrackFacade(final TrackFacade trackFacade) {
        this.trackFacade = trackFacade;
    }
}
