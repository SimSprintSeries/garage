package com.sss.garage.service.auth.user.impl;

import java.util.Optional;

import com.sss.garage.model.role.DiscordRole;
import com.sss.garage.model.user.DiscordUser;
import com.sss.garage.model.user.DiscordUserRepository;
import com.sss.garage.service.auth.role.RoleService;
import com.sss.garage.service.auth.user.UserService;
import com.sss.garage.service.session.SessionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SssUserService implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(SssUserService.class);

    private DiscordUserRepository userRepository;

    private SessionService sessionService;

    private RoleService roleService;

    @Override
    public Boolean isCurrentlyLoggedInUser(final DiscordUser discordUser) {
        return discordUser != null && discordUser.equals(getAuthentication().getPrincipal());
    }

    @Override // TODO: Store in session on login
    public Boolean isCurrentUserAdmin() {
        return getAuthentication().getAuthorities().stream()
                .filter(a -> a instanceof DiscordRole)
                .map(DiscordRole.class::cast)
                .anyMatch(r -> roleService.getAdminRole().equals(r));
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public Optional<DiscordUser> getCurrentUser() {
        try {
            return Optional.of(sessionService.getCurrentUser());
        }
        catch (RuntimeException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<DiscordUser> findUserById(final String id) {
        return findUserById(Long.valueOf(id));
    }

    @Override
    public Optional<DiscordUser> findUserById(final Long id) {
        return userRepository.findById(id);
    }

    @Override
    public DiscordUser saveUser(final DiscordUser discordUser) {
        return userRepository.save(discordUser);
    }

    @Override
    public void revokeUserToken(final DiscordUser discordUser) {
        discordUser.setCurrentJwtToken(null);
        discordUser.setTokenExpiryDate(null);
        userRepository.save(discordUser);
    }

    @Override
    public void deprecateUserRoles(final Long id) {
        findUserById(id).ifPresentOrElse(u -> {
                        u.setRolesUpToDate(false);
                        userRepository.save(u);
                },
                () -> logger.error("Attempt to deprecate non-existing driver's roles: " + id));
    }

    @Autowired
    public void setUserRepository(final DiscordUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setSessionService(final SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Autowired
    public void setRoleService(final RoleService roleService) {
        this.roleService = roleService;
    }
}
