package com.sss.garage.controller.filter;

import static com.sss.garage.constants.WebConstants.AUTHORIZATION_HEADER_BEARER_PREFIX;
import static org.apache.tomcat.websocket.Constants.AUTHORIZATION_HEADER_NAME;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

import com.sss.garage.service.auth.jwt.impl.SSSJwtTokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private SSSJwtTokenService jwtTokenService;
    private ConversionService conversionService;

    @Autowired
    public JwtAuthorizationFilter(final AuthenticationManager authenticationManager) {
        super(authenticationManager);
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
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(req));

        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws AccessDeniedException {
        String token = request.getHeader(AUTHORIZATION_HEADER_NAME).replace(AUTHORIZATION_HEADER_BEARER_PREFIX, "");

        return jwtTokenService.extractUserFromToken(token)
                .map(u -> conversionService.convert(u, UsernamePasswordAuthenticationToken.class))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Autowired
    public void setJwtTokenService(final SSSJwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Autowired
    public void setConversionService(final ConversionService conversionService) {
        this.conversionService = conversionService;
    }
}