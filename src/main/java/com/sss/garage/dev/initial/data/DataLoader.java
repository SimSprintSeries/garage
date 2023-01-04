package com.sss.garage.dev.initial.data;

import com.sss.garage.model.role.RoleRepository;
import com.sss.garage.model.user.DiscordUser;
import com.sss.garage.model.user.UserRepository;
import com.sss.garage.service.discord.api.DiscordApiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataLoader {

    private final DiscordApiService discordApiService;

    @Autowired
    public DataLoader(final UserRepository userRepository,
                      final RoleRepository roleRepository,
                      final DiscordApiService discordApiService) {
        this.discordApiService = discordApiService;
        loadInitialData(userRepository, roleRepository);
    }

    public void loadInitialData(final UserRepository userRepository, final RoleRepository roleRepository) {
        discordApiService.persistAllRoles();
        userRepository.save(newUser("507654703568519168")); //Rychu Peja solo
    }

    private DiscordUser newUser(final String id) {
        final DiscordUser user = new DiscordUser();
        user.setId(id);
        return user;
    }
}
