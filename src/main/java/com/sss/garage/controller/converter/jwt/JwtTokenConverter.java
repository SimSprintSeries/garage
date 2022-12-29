package com.sss.garage.controller.converter.jwt;

import com.sss.garage.data.auth.JwtTokenData;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenConverter implements Converter<String, JwtTokenData> {

    @Override
    public JwtTokenData convert(final String token) {
        final JwtTokenData jwtTokenData = new JwtTokenData();
        jwtTokenData.setToken(token);
        return jwtTokenData;
    }
}
