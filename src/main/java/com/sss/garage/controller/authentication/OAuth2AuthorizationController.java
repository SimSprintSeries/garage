package com.sss.garage.controller.authentication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/oauth2/authorization/discord")
@Tag(name = "Sss OAuth2 Authorization")
public class OAuth2AuthorizationController {

    @GetMapping
    @Operation(operationId = "obtainAuthorizationToken",
            summary = "Obtain OAuth2 redirection link to discord. Comes with 'oauth2_auth_request' cookie to know that you're the one who should receive token.",
            description = "This request returns redirect link to discord oauth API, where user can authorize us - garage API - to access his account data. " +
                    "Redirection link contains link to where discord should redirect user after authorization.",
            responses = @ApiResponse(responseCode = "302", description = "Successful request always responds with 302 redirect and a 'oauth2_auth_request' cookie."))
    public void obtainAuthorizationToken() {
        // placeholder for documentation, all is done in the filters thanks to spring <3 (joke, I hate that filters idea)
    }
}
