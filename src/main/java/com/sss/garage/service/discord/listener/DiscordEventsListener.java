package com.sss.garage.service.discord.listener;

import com.sss.garage.service.auth.role.RoleService;
import com.sss.garage.service.auth.user.UserService;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.events.role.RoleCreateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiscordEventsListener extends ListenerAdapter {
    private RoleService roleService;
    private UserService userService;

    @Override
    public void onRoleCreate(final RoleCreateEvent event) {
        final Role role = event.getRole();
        roleService.saveRole(role.getId(), role.getName());
    }

    @Override
    public void onGuildMemberRoleAdd(final GuildMemberRoleAddEvent event) {
        if(!event.getRoles().isEmpty()) {
            userService.deprecateUserRoles(event.getMember().getIdLong());
        }
    }

    @Override
    public void onGuildMemberRoleRemove(final GuildMemberRoleRemoveEvent event) {
        if(!event.getRoles().isEmpty()) {
            userService.deprecateUserRoles(event.getMember().getIdLong());
        }
    }

    @Autowired
    public void setRoleService(final RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }
}
