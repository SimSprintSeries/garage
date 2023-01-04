package com.sss.garage.service.auth.user;

import java.util.Optional;

import com.sss.garage.model.user.DiscordUser;

public interface UserService {
    Optional<DiscordUser> findUserById(final String Username);
    DiscordUser saveUser(final DiscordUser discordUser);
}
