package com.sss.garage.service.auth.role.impl;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sss.garage.model.role.Role;
import com.sss.garage.model.user.User;
import com.sss.garage.service.auth.role.RoleService;

import org.springframework.stereotype.Service;

@Service
public class SSSRoleService implements RoleService {

    // TODO: Probably there exists better way to do that?
    @Override
    public Set<Role> getAllRolesFromUser(final User user) {
        return Stream.concat(user.getRoles().stream(),
                        user.getRoles().stream()
                                .map(Role::getRoles)
                                .flatMap(Set::stream))
                .collect(Collectors.toSet());
    }
}
