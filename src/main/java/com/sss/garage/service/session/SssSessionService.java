package com.sss.garage.service.session;

import com.sss.garage.model.user.DiscordUser;
import com.sss.garage.service.auth.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SssSessionService implements SessionService {

    private UserService userService;
    private ConversionService conversionService;

    @Override
    public DiscordUser getCurrentUser() {
        final Object principal = getCurrentAuthentication().getPrincipal();
        if(principal instanceof DiscordUser) {
            return (DiscordUser) principal;
        }
        else if (principal instanceof Authentication) {
            // principal instance of DiscordOAuth2User
            // could not yet be in the database...
            return userService.saveUser(conversionService.convert(((Authentication)principal).getPrincipal(), DiscordUser.class));
        }

        throw new RuntimeException("Authentication error. Contact SSS Admins");
    }

    @Override
    public Authentication getCurrentAuthentication() {
        return SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication();
    }

    @Override
    public void setCurrentAuthentication(final Authentication authentication) {
        SecurityContextHolder.getContextHolderStrategy().getContext().setAuthentication(authentication);
    }

    @Autowired
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setConversionService(final ConversionService conversionService) {
        this.conversionService = conversionService;
    }
}
