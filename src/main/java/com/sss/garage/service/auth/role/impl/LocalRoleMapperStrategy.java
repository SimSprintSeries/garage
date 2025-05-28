package com.sss.garage.service.auth.role.impl;

import com.sss.garage.service.auth.role.RoleMapperStrategy;

import org.springframework.stereotype.Component;

@Component("localRoleMapperStrategy")
public class LocalRoleMapperStrategy implements RoleMapperStrategy {

    @Override
    public String admin() {
        return "ROLE_1075533713233948722";
    }

    @Override
    public String steward() {
        return "ROLE_1075533713233948722";
    }

    @Override
    public String user() {
        return "ROLE_1059454168525447178";
    }
}
