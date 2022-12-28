package com.sss.garage.configuration.web;

import com.sss.garage.controller.converter.jwt.JwtTokenConverter;
import com.sss.garage.controller.converter.user.UserToAuthenticationTokenConverter;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new JwtTokenConverter());
        registry.addConverter(new UserToAuthenticationTokenConverter());
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
