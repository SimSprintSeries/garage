package com.sss.garage.service.discord.api.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.sss.garage.model.role.DiscordRole;
import com.sss.garage.model.role.RoleRepository;
import com.sss.garage.service.discord.api.DiscordApiService;
import com.sss.garage.service.discord.listener.DiscordEventsListener;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class SssDiscordApiService implements DiscordApiService {
    private JDA jda;
    private ConversionService conversionService;
    private RoleRepository roleRepository;
    private DiscordEventsListener discordEventsListener;

    @Value("${discord.api.bot.token}")
    private String DISCORD_API_TOKEN;

    @Value("${discord.sss.guild.id}")
    private String SSS_GUILD_ID;

    @PostConstruct
    private void init() throws InterruptedException {
        jda = JDABuilder.createDefault(DISCORD_API_TOKEN)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .setChunkingFilter(ChunkingFilter.ALL)
                .addEventListeners(discordEventsListener)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .build().awaitReady();// TODO: optimize and think how to make it usable
    }

    public Guild sssGuild() {
        return jda.getGuildById(SSS_GUILD_ID);
    }

    @Override
    public List<Role> getAllSssRoles() {
        return sssGuild().getRoles();
    }

    @Override
    public void persistAllRoles() {
        roleRepository.saveAll(getAllSssRoles().stream()
                .map(r -> conversionService.convert(r, DiscordRole.class))
                .collect(Collectors.toSet()));
    }

    public Optional<Member> findUserByUserId(Long uid) {
        return sssGuild().loadMembers().get().stream()
                .filter(m -> uid.equals(m.getIdLong()))
                .findFirst();
    }

    public Set<DiscordRole> findAllRolesForUserId(Long uid) {
        return findUserByUserId(uid)
                .map(m -> m.getRoles().stream()
                        .map(r -> conversionService.convert(r, DiscordRole.class))
                        .collect(Collectors.toSet()))
                .orElseThrow(() -> new UsernameNotFoundException(uid.toString()));
    }

    @Autowired
    public void setConversionService(final ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Autowired
    public void setRoleRepository(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setDiscordEventsListener(final DiscordEventsListener discordEventsListener) {
        this.discordEventsListener = discordEventsListener;
    }
}
