package com.sss.garage.service.auth.jwt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SssPasswordEncoder implements PasswordEncoder {

    private final PasswordEncoder passwordEncoder;

    public SssPasswordEncoder() {
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encode(final CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(final CharSequence rawPassword, final String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
