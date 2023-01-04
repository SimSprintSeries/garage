package com.sss.garage.controller.filter;

import java.io.IOException;
import java.lang.reflect.Field;


import org.springframework.core.log.LogMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.context.SecurityContextRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class OAuth2LoginAuthenticationContinueChainFilter extends OAuth2LoginAuthenticationFilter {

    public OAuth2LoginAuthenticationContinueChainFilter(final ClientRegistrationRepository clientRegistrationRepository,
                                                        final OAuth2AuthorizedClientService authorizedClientService,
                                                        final AuthenticationManager authenticationManager,
                                                        final String filterProcessesUrl) {
        super(clientRegistrationRepository, authorizedClientService, filterProcessesUrl);
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain, final Authentication authResult) throws IOException, ServletException {
        try {
            // Stupid spring and it's private fields
            Field securityContextHolderStrategy = AbstractAuthenticationProcessingFilter.class.getDeclaredField("securityContextHolderStrategy");
            securityContextHolderStrategy.setAccessible(true);
            Field securityContextRepository = AbstractAuthenticationProcessingFilter.class.getDeclaredField("securityContextRepository");
            securityContextRepository.setAccessible(true);

            // Same as original implementation
            SecurityContext context = ((SecurityContextHolderStrategy)securityContextHolderStrategy.get(this)).createEmptyContext();
            context.setAuthentication(authResult);
            ((SecurityContextHolderStrategy)securityContextHolderStrategy.get(this)).setContext(context);
            ((SecurityContextRepository)securityContextRepository.get(this)).saveContext(context, request, response);
            if (this.logger.isDebugEnabled()) {
                this.logger.debug(LogMessage.format("Set SecurityContextHolder to %s", authResult));
            }

            // empty handler anyway
//            this.rememberMeServices.loginSuccess(request, response, authResult);
            if (this.eventPublisher != null) {
                this.eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(authResult, this.getClass()));
            }

            // We don't want a redirect, we want to continue with filter chain!
//            this.successHandler.onAuthenticationSuccess(request, response, authResult);
            chain.doFilter(request, response);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace(); //Never going in there unless spring is upgraded
        }
    }
}
