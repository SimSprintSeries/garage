package com.sss.garage.service.discord.api;

import java.util.List;
import java.util.Set;

import com.sss.garage.model.role.DiscordRole;

import net.dv8tion.jda.api.entities.Role;

public interface DiscordApiService {
    List<Role> getAllSssRoles();
    void persistAllRoles();
    Set<DiscordRole> findAllRolesForUserId(String uid);
}
