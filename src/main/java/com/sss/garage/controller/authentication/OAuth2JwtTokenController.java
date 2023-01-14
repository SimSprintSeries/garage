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

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/login/oauth2/code/discord")
@Api(tags = "Sss OAuth2 Jwt Token")
public class OAuth2JwtTokenController extends SssBaseController {

    private AuthenticationFacade authenticationFacade;

    @GetMapping
    public JwtTokenDTO token() {
        final JwtTokenData token = authenticationFacade.getJwtTokenForCurrentUser();

        return mapper.map(token, JwtTokenDTO.class);
    }

    @PostMapping("/revoke")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void revokeToken(@RequestParam final String token) {
        authenticationFacade.revokeToken(token);
    }

    @Autowired
    public void setAuthenticationFacade(final AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }
}
