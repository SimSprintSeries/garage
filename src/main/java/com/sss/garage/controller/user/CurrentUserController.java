package com.sss.garage.controller.user;

import static com.sss.garage.constants.WebConstants.USER_ENDPOINT;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.dto.user.BasicDiscordUserDTO;
import com.sss.garage.facade.user.UserFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(USER_ENDPOINT)
@Tag(name = "Sss CurrentUser")
public class CurrentUserController extends SssBaseController {

    private UserFacade userFacade;

    @GetMapping("/basic")
    @Operation(operationId = "getCurrentUserBasicData", summary = "Get basic info about current user")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BasicDiscordUserDTO.class)))
    public BasicDiscordUserDTO getCurrentUserBasicData() {
        return mapper.map(userFacade.getCurrentUser(), BasicDiscordUserDTO.class);
    }

    @Autowired
    public void setUserFacade(final UserFacade userFacade) {
        this.userFacade = userFacade;
    }
}
