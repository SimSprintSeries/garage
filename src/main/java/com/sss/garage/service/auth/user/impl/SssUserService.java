package com.sss.garage.service.auth.user.impl;

import java.util.Optional;

import com.sss.garage.model.user.DiscordUser;
import com.sss.garage.model.user.DiscordUserRepository;
import com.sss.garage.service.auth.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SssUserService implements UserService {

    private DiscordUserRepository userRepository;

    @Override
    public Optional<DiscordUser> findUserById(final String id) {
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

    @Autowired
    public void setUserRepository(final DiscordUserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
