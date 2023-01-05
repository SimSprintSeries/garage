package com.sss.garage.service.auth.role.impl;

import java.util.Optional;

import com.sss.garage.model.role.DiscordRole;
import com.sss.garage.model.role.RoleRepository;
import com.sss.garage.service.auth.role.RoleMapperStrategy;
import com.sss.garage.service.auth.role.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

@Service
public class SssRoleService implements RoleService {

    private RoleRepository roleRepository;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    private RoleMapperStrategy localMapperStrategy;
    private RoleMapperStrategy sssMapperStrategy;

    @Override
    public RoleMapperStrategy getRoleMapperStrategy() {
        switch (activeProfile){
            case "local":
                return localMapperStrategy;
            case "dev":
            case "prod":
                return sssMapperStrategy;
            default:
                throw new UnsupportedOperationException("Unknown spring profile");
        }
    }

    @Override
    public Optional<DiscordRole> findById(final String id) {
        return roleRepository.findById(id);
    }

    @Autowired
    public void setRoleRepository(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Resource(name = "localRoleMapperStrategy")
    public void setLocalMapperStrategy(final RoleMapperStrategy localMapperStrategy) {
        this.localMapperStrategy = localMapperStrategy;
    }

    @Resource(name = "sssRoleMapperStrategy")
    public void setSssMapperStrategy(final RoleMapperStrategy sssMapperStrategy) {
        this.sssMapperStrategy = sssMapperStrategy;
    }
}
