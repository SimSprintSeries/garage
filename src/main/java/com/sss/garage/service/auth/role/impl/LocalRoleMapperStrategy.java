package com.sss.garage.service.auth.role.impl;

import com.sss.garage.service.auth.role.RoleMapperStrategy;

import org.springframework.stereotype.Component;

@Component("localRoleMapperStrategy")
public class LocalRoleMapperStrategy implements RoleMapperStrategy {

    @Override
    public String admin() {
        return null;
    }

    @Override
    public String user() {
        return "1059454168525447178";
    }
}
