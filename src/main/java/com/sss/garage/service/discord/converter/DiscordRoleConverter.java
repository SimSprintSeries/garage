package com.sss.garage.service.discord.converter;

import com.sss.garage.model.role.DiscordRole;

import net.dv8tion.jda.api.entities.Role;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DiscordRoleConverter implements Converter<Role, DiscordRole> {

    @Override
    public DiscordRole convert(final Role source) {
        DiscordRole target = new DiscordRole();
        target.setId("ROLE_" + source.getId());
        target.setCode(source.getName());
        return target;
    }
}
