package com.sss.garage.service.user.impl;

import com.sss.garage.model.user.DiscordUser;
import com.sss.garage.model.user.DiscordUserRepository;
import com.sss.garage.service.user.DiscordUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SssDiscordUserService implements DiscordUserService {
    private DiscordUserRepository discordUserRepository;

    @Override
    public List<DiscordUser> getAllDiscordUsers() {
        return discordUserRepository.findAll();
    }

    @Override
    public Optional<DiscordUser> getDiscordUser(final Long id) {
        return discordUserRepository.findById(id);
    }

    @Autowired
    public void setDiscordUserRepository (DiscordUserRepository discordUserRepository) {
        this.discordUserRepository = discordUserRepository;
    }
}
