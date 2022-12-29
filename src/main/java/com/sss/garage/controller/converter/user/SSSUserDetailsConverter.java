package com.sss.garage.controller.converter.user;

import com.sss.garage.model.user.User;
import com.sss.garage.model.user.auth.SSSUserDetails;
import com.sss.garage.service.auth.role.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SSSUserDetailsConverter implements Converter<User, SSSUserDetails> {

    private RoleService roleService;

    @Override
    public SSSUserDetails convert(final User source) {
        SSSUserDetails userDetails = new SSSUserDetails();
        userDetails.setUsername(source.getUsername());
        userDetails.setPassword(source.getPassword());
        userDetails.setActive(source.isActive());
        userDetails.setAuthorities(roleService.getAllRolesFromUser(source));
        return userDetails;
    }

    @Autowired
    public void setRoleService(final RoleService roleService) {
        this.roleService = roleService;
    }
}
