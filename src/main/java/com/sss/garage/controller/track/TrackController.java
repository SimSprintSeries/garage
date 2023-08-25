package com.sss.garage.controller.track;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.data.track.TrackData;
import com.sss.garage.dto.track.TrackDTO;
import com.sss.garage.facade.track.TrackFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sss.garage.constants.WebConstants.*;

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

    @GetMapping("/paginated")
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "getTracksPaginated", summary = "Get all tracks paginated")
    public Page<TrackDTO> getTracksPaginated(@Parameter(description = "The current result page requested") @RequestParam(value = "currentPage", defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
                                             @Parameter(description = "The number of results returned per page") @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
                                             @Parameter(description = "Sorting method applied to the returned results") @RequestParam(value = "sort", defaultValue = "id") final String sort,
                                             @Parameter(description = "Sorting direction", schema = @Schema(description = "sort", type = "String", allowableValues = "ASC,DESC")) @RequestParam(value = "sortDirection", defaultValue = "ASC") final String sortDirection,
                                             @Parameter(description = "Optional track name to filter by") @RequestParam(value = "name", required = false) final String name,
                                             @Parameter(description = "Optional country name to filter by") @RequestParam(value = "country", required = false) final String country) {
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sort));
        return this.trackFacade.getTracksPaginated(name, country, pageable).map(t -> mapper.map(t, TrackDTO.class));
    }

    @Autowired
    public void setTrackFacade(final TrackFacade trackFacade) {
        this.trackFacade = trackFacade;
    }
}
