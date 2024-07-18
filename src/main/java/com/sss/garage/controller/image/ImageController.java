package com.sss.garage.controller.image;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.dto.image.ImageDTO;
import com.sss.garage.facade.image.ImageFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.sss.garage.constants.WebConstants.IMAGE_ENDPOINT;

@RestController
@RequestMapping(IMAGE_ENDPOINT)
@Tag(name = "Sss Image")
public class ImageController extends SssBaseController {
    private ImageFacade imageFacade;

    @GetMapping
    @Operation(operationId = "getImageByLeague", summary = "Get game information")
    @ResponseStatus(HttpStatus.OK)
    public ImageDTO getImageByLeague(@Parameter(description = "League ID for images") @RequestParam(value = "leagueId") final String leagueId) {
        return mapper.map(imageFacade.getImage(leagueId), ImageDTO.class);
    }

    @Autowired
    public void setImageFacade(final ImageFacade imageFacade) {
        this.imageFacade = imageFacade;
    }
}
