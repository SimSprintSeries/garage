package com.sss.garage.service.auth.user;

import java.util.Optional;

import com.sss.garage.model.user.DiscordUser;

public interface UserService {
    Optional<DiscordUser> findUserById(final String Username);
    Optional<DiscordUser> findUserById(final Long Username);
    DiscordUser saveUser(final DiscordUser discordUser);
    void revokeUserToken(final DiscordUser discordUser);
    void deprecateUserRoles(final Long id);
}
