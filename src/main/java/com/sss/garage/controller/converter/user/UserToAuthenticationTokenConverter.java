package com.sss.garage.controller.converter.user;

import java.util.ArrayList;
import java.util.List;

import com.sss.garage.model.permission.Permission;
import com.sss.garage.model.user.User;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UserToAuthenticationTokenConverter implements Converter<User, UsernamePasswordAuthenticationToken> {

    @Override
    public UsernamePasswordAuthenticationToken convert(final User source) {
        final List<Permission> permissions = new ArrayList<>(); //TODO: get actual permissions from user

        return new UsernamePasswordAuthenticationToken(source.getUsername(), null, permissions);
    }
}
