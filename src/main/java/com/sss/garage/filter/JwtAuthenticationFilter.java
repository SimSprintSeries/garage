package com.sss.garage.filter;

import static com.sss.garage.constants.WebConstants.AUTHORIZATION_HEADER_BEARER_PREFIX;
import static org.apache.tomcat.websocket.Constants.AUTHORIZATION_HEADER_NAME;

import java.io.IOException;
import java.time.Instant;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.sss.garage.service.auth.jwt.JwtTokenService;
import com.sss.garage.service.session.SessionService;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenService jwtTokenService;
    private final SessionService sessionService;

    public JwtAuthenticationFilter(final JwtTokenService jwtTokenService, final SessionService sessionService) {
        this.jwtTokenService = jwtTokenService;
        this.sessionService = sessionService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(AUTHORIZATION_HEADER_NAME);

        if (header == null || !header.startsWith(AUTHORIZATION_HEADER_BEARER_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }
        sessionService.setCurrentAuthentication(getAuthentication(req));

        chain.doFilter(req, res);
    }

    private Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION_HEADER_NAME).replace(AUTHORIZATION_HEADER_BEARER_PREFIX, "");
        return jwtTokenService.extractAuthenticationFromToken(token).orElseThrow(() -> new AccessDeniedException("Token invalid"));
    }
}