package com.sss.garage.service.auth.role;

import java.util.Set;

import com.sss.garage.model.role.Role;
import com.sss.garage.model.user.User;

public interface RoleService {
    Set<Role> getAllRolesFromUser(final User user);
}
