package com.sss.garage.service.user;

import com.sss.garage.model.user.DiscordUser;

import java.util.List;
import java.util.Optional;

public interface DiscordUserService {
    Optional<DiscordUser> getDiscordUser(final Long id);

    List<DiscordUser> getAllDiscordUsers();
}
