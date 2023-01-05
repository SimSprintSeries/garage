package com.sss.garage.configuration.security;

import static com.sss.garage.constants.WebConstants.NON_ACCESSIBLE_PATH;

import com.sss.garage.controller.filter.JwtAuthenticationFilter;
import com.sss.garage.controller.filter.OAuth2LoginAuthenticationContinueChainFilter;
import com.sss.garage.service.auth.jwt.JwtTokenService;
import com.sss.garage.service.auth.role.RoleMapperStrategy;
import com.sss.garage.service.auth.role.RoleService;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private JwtTokenService jwtTokenService;
    private RoleMapperStrategy roles;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           ClientRegistrationRepository clientRegistrationRepository,
                                           final OAuth2AuthorizedClientService authorizedClientService,
                                           final AuthenticationManager authenticationManager,
                                           OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService) throws Exception {
        http
                .exceptionHandling()
                    .defaultAuthenticationEntryPointFor(getRestAuthenticationEntryPoint(), new AntPathRequestMatcher("/api/**")).and()
                .oauth2Client(oauth2 -> oauth2
                        .clientRegistrationRepository(clientRegistrationRepository))
                .oauth2Login(oauth2 -> oauth2
//                        .authorizedClientService() TODO: Implement JPAAuthorizedClientService
                        .loginProcessingUrl(NON_ACCESSIBLE_PATH) // Disable original OAuth2LoginAuthenticationFilter
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(oAuth2UserService)))
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/login/oauth2/code/discord").authenticated() // Will authenticate in filters before returning jwt to user
//                        .requestMatchers("/**").hasRole(roles.user()) //example
                        .anyRequest().permitAll())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(sssOAuth2LoginAuthenticationFilter(clientRegistrationRepository, authorizedClientService, authenticationManager), OAuth2LoginAuthenticationFilter.class)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .anonymous().disable()
                .csrf().disable();
        return http.build();
    }

    private AuthenticationEntryPoint getRestAuthenticationEntryPoint() {
        return new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED);
    }

    @Value("${discord.api.bot.token}")
    private String DISCORD_API_TOKEN;

    @Bean
    public JDA jda() throws InterruptedException {
        return JDABuilder.createDefault(DISCORD_API_TOKEN)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .build().awaitReady();// TODO: optimize and think how to make it usable
    }

    // Dummy auth manager, real one is created in filterChain() and then replaced in OAuth2LoginAuthenticationContinueChainFilterBeanPostProcessor
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        return authenticationManagerBuilder.build();
    }

    public OAuth2LoginAuthenticationFilter sssOAuth2LoginAuthenticationFilter(final ClientRegistrationRepository clientRegistrationRepository, final OAuth2AuthorizedClientService authorizedClientService, final AuthenticationManager authenticationManager) {
        return new OAuth2LoginAuthenticationContinueChainFilter(clientRegistrationRepository, authorizedClientService, authenticationManager, "/login/oauth2/code/*");
    }

    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(this.jwtTokenService);
    }

    @Autowired
    public void setJwtTokenService(final JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Autowired
    public void setRoles(final RoleService roleService) {
        this.roles = roleService.getRoleMapperStrategy();
    }
}
