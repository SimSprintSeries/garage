package com.sss.garage.service.auth.role.impl;

import com.sss.garage.service.auth.role.RoleMapperStrategy;

import org.springframework.stereotype.Component;

@Component("sssRoleMapperStrategy")
public class SssRoleMapperStrategy implements RoleMapperStrategy {

    @Override
    public String admin() {
        return null;
    }

    @Override
    public String user() {
        return null;
    }
}
