package com.sss.garage.service.auth.role;

import java.util.Optional;
import java.util.Set;

import com.sss.garage.model.role.DiscordRole;
import com.sss.garage.model.user.DiscordUser;
import com.sss.garage.service.discord.converter.DiscordRoleConverter;

public interface RoleService {
    Optional<DiscordRole> findById(final String id);
    RoleMapperStrategy getRoleMapperStrategy();
}
