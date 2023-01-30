package com.sss.garage.service.auth.jwt;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SssPasswordEncoder implements PasswordEncoder {

    private final PasswordEncoder passwordEncoder;

    public SssPasswordEncoder() {
        passwordEncoder = new Argon2PasswordEncoder(16, 32, 1, 4096, 3);
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
