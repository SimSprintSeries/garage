package com.sss.garage.service.auth.user.impl;

import java.util.Optional;

import com.sss.garage.model.user.DiscordUser;
import com.sss.garage.model.user.DiscordUserRepository;
import com.sss.garage.service.auth.user.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SssUserService implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(SssUserService.class);

    private DiscordUserRepository userRepository;

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
        userRepository.save(discordUser);
    }

    @Override
    public void deprecateUserRoles(final Long id) {
        findUserById(id).ifPresentOrElse(u -> {
                        u.setRolesUpToDate(false);
                        userRepository.save(u);
                },
                () -> logger.error("Attempt to deprecate non-existing user's roles: " + id));
    }

    @Autowired
    public void setUserRepository(final DiscordUserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
