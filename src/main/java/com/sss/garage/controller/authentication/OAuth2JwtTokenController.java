package com.sss.garage.controller.authentication;

import com.sss.garage.controller.SssBaseController;
import com.sss.garage.data.auth.JwtTokenData;
import com.sss.garage.dto.auth.JwtTokenDTO;
import com.sss.garage.facade.auth.AuthenticationFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/login/oauth2/code/discord")
@Tag(name = "Sss OAuth2 Jwt Token")
public class OAuth2JwtTokenController extends SssBaseController {

    private AuthenticationFacade authenticationFacade;

    @GetMapping
    @Operation(operationId = "token",
            summary = "Provides JWT token for logged in user. Requires code and state parameters from discord OAuth2 API. It also requires cookie which is set in obtainAuthorizationToken request",
            description = "Second request required for successful OAuth2 authentication. " +
                    "It takes the 'oauth2_auth_request' cookie to confirm that you are the one who requested to login in the first place. " +
                    "It takes code and state parameters passed from discord, to gather necessary data about the user to provide him with JWT Token.",
            responses = @ApiResponse(responseCode = "302", description = "Successful request always responds with 302 redirect and cookie."))
    public JwtTokenDTO token(@Parameter(description = "code parameter passed by discord OAuth2 API", required = true) @RequestParam final String code,
                             @Parameter(description = "state parameter passed by discord OAuth2 API", required = true) @RequestParam final String state) {
        final JwtTokenData token = authenticationFacade.getJwtTokenForCurrentUser();

        return mapper.map(token, JwtTokenDTO.class);
    }

    @PostMapping("/revoke")
    @Operation(summary = "Revoke token for user. This token will no longer provide user with authentication. Kind of like log-out request.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void revokeToken(@RequestParam final String token) {
        authenticationFacade.revokeToken(token);
    }

    @Autowired
    public void setAuthenticationFacade(final AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }
}
