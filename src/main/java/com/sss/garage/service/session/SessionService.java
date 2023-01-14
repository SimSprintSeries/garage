package com.sss.garage.service.session;

import com.sss.garage.model.user.DiscordUser;

import org.springframework.security.core.Authentication;

public interface SessionService {
    DiscordUser getCurrentUser();
    Authentication getCurrentAuthentication();
    void setCurrentAuthentication(Authentication authentication);
}
