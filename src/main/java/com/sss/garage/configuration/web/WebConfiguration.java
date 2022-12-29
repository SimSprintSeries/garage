package com.sss.garage.configuration.web;

import com.sss.garage.controller.converter.jwt.JwtTokenConverter;
import com.sss.garage.controller.converter.user.SSSUserDetailsConverter;
import com.sss.garage.controller.converter.user.UserToAuthenticationTokenConverter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private JwtTokenConverter jwtTokenConverter;
    private UserToAuthenticationTokenConverter userToAuthenticationTokenConverter;
    private SSSUserDetailsConverter sssUserDetailsConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(jwtTokenConverter);
        registry.addConverter(userToAuthenticationTokenConverter);
        registry.addConverter(sssUserDetailsConverter);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Autowired
    public void setJwtTokenConverter(final JwtTokenConverter jwtTokenConverter) {
        this.jwtTokenConverter = jwtTokenConverter;
    }

    @Autowired
    public void setUserToAuthenticationTokenConverter(final UserToAuthenticationTokenConverter userToAuthenticationTokenConverter) {
        this.userToAuthenticationTokenConverter = userToAuthenticationTokenConverter;
    }

    @Autowired
    public void setSssUserDetailsConverter(final SSSUserDetailsConverter sssUserDetailsConverter) {
        this.sssUserDetailsConverter = sssUserDetailsConverter;
    }
}
