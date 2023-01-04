package com.sss.garage.service.auth.role.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.sss.garage.model.role.DiscordRole;
import com.sss.garage.model.role.RoleRepository;
import com.sss.garage.model.user.DiscordUser;
import com.sss.garage.service.auth.role.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SssRoleService implements RoleService {

    private RoleRepository roleRepository;

    // TODO: Probably there exists better way to do that?
    @Override
    public Set<DiscordRole> getAllRolesFromUser(final DiscordUser user) {
//        return Stream.concat(user.getRoles().stream(),
//                        user.getRoles().stream()
//                                .map(DiscordRole::getRoles)
//                                .flatMap(Set::stream))
//                .collect(Collectors.toSet());
        return new HashSet<>();
    }

    public Optional<DiscordRole> findById(final String id) {
        return roleRepository.findById(id);
    }

    @Autowired
    public void setRoleRepository(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
