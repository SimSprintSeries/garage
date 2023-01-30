package com.sss.garage.controller.filter;

import static com.sss.garage.constants.WebConstants.AUTHENTICATION_TOKEN_CHANGED_HEADER_NAME;

import java.io.IOException;
import java.util.Objects;

import com.sss.garage.data.auth.JwtTokenData;
import com.sss.garage.model.user.DiscordUser;
import com.sss.garage.model.user.auth.DiscordOAuth2User;
import com.sss.garage.service.auth.jwt.JwtTokenService;
import com.sss.garage.service.auth.jwt.StringJwtAuthenticationToken;
import com.sss.garage.service.auth.user.UserService;
import com.sss.garage.service.discord.api.DiscordApiService;
import com.sss.garage.service.session.SessionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Generates new JWT token for already authenticated user if his roles on discord have changed
 * It will also replace old authentication with new in the session
 */
public class GenerateNewJwtTokenFilter extends OncePerRequestFilter {
    Logger logger = LoggerFactory.getLogger(GenerateNewJwtTokenFilter.class);

    private final SessionService sessionService;
    private final JwtTokenService jwtTokenService;
    private final DiscordApiService discordApiService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public GenerateNewJwtTokenFilter(final SessionService sessionService, final JwtTokenService jwtTokenService,
                                     final DiscordApiService discordApiService, final UserService userService,
                                     final PasswordEncoder passwordEncoder) {
        this.sessionService = sessionService;
        this.jwtTokenService = jwtTokenService;
        this.discordApiService = discordApiService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {
        final Authentication authentication = sessionService.getCurrentAuthentication();
        if(Objects.isNull(authentication)) {
            filterChain.doFilter(request, response);
        }

        final Object principal = authentication.getPrincipal();
        if (principal instanceof DiscordUser && !((DiscordUser) principal).isRolesUpToDate()){
            // Roles have been updated! New token must be generated
            final DiscordUser user = (DiscordUser) principal;

            final StringJwtAuthenticationToken newAuthentication = new StringJwtAuthenticationToken(user,
                    discordApiService.findAllRolesForUserId(user.getId()));

            JwtTokenData replacementToken = jwtTokenService.generateForAuthentication(newAuthentication);
            response.addHeader(AUTHENTICATION_TOKEN_CHANGED_HEADER_NAME, replacementToken.getToken());
//            response.addHeader(AUTHENTICATION_TOKEN_EXPIRY_HEADER_NAME, replacementToken.getExpiresAt()); // not needed right now

            sessionService.setCurrentAuthentication(newAuthentication);

            // TODO: publish token changed event?
            user.setRolesUpToDate(true);
            user.setCurrentJwtToken(passwordEncoder.encode(replacementToken.getToken()));
            user.setTokenExpiryDate(replacementToken.getExpiresAt());
            userService.saveUser(user);
        }
        else {
            if(!(principal instanceof DiscordOAuth2User || principal instanceof DiscordUser)) { // just a check
                logger.error("Principal of unhandled type: " + principal.getClass());
            }
            // New token has been generated, leave role setting to the controller
        }
        filterChain.doFilter(request, response);
    }
}
