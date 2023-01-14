package com.sss.garage.service.session;

import com.sss.garage.model.user.DiscordUser;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SssSessionService implements SessionService {

    @Override
    public DiscordUser getCurrentUser() {
        return (DiscordUser) getCurrentAuthentication().getPrincipal();
    }

    @Override
    public Authentication getCurrentAuthentication() {
        return SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication();
    }

    @Override
    public void setCurrentAuthentication(final Authentication authentication) {
        SecurityContextHolder.getContextHolderStrategy().getContext().setAuthentication(authentication);
    }
}
