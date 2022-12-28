package com.sss.garage.controller.authentication;

import com.sss.garage.controller.SSSBaseController;
import com.sss.garage.data.auth.JwtTokenData;
import com.sss.garage.dto.auth.JwtTokenDTO;
import com.sss.garage.facade.auth.AuthenticationFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/auth")
@Api(tags = "SSS Authentication")
public class AuthenticationController extends SSSBaseController {

    private AuthenticationFacade authenticationFacade;

    @PostMapping("/token")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            nickname = "Authenticate",
            value = "Receive authentication token for user",
            notes = "200 if successful, 401 otherwise"
    )
    public JwtTokenDTO authenticate(@ApiParam(value="Username", required = true) @RequestParam final String username,
                                    @ApiParam(value="Password", required = true) @RequestParam final String password) throws Exception {
        final JwtTokenData token = authenticationFacade.authenticate(username, password);

        return mapper.map(token, JwtTokenDTO.class);
    }

    @Autowired
    public void setAuthenticationFacade(final AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }
}
