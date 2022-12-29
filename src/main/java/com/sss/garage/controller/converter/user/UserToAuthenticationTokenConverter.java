package com.sss.garage.controller.converter.user;

import com.sss.garage.model.user.User;
import com.sss.garage.service.auth.role.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class UserToAuthenticationTokenConverter implements Converter<User, UsernamePasswordAuthenticationToken> {

    private RoleService roleService;

    @Override
    public UsernamePasswordAuthenticationToken convert(final User source) {
        return new UsernamePasswordAuthenticationToken(source.getUsername(), null, roleService.getAllRolesFromUser(source));
    }

    @Autowired
    public void setRoleService(final RoleService roleService) {
        this.roleService = roleService;
    }
}
