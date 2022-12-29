package com.sss.garage.dev.initial.data;

import java.util.List;
import java.util.Set;

import com.sss.garage.model.role.Role;
import com.sss.garage.model.role.RoleRepository;
import com.sss.garage.model.user.User;
import com.sss.garage.model.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DataLoader {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataLoader(final UserRepository userRepository,
                      final RoleRepository roleRepository,
                      final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        loadInitialData(userRepository, roleRepository);
    }

    public void loadInitialData(final UserRepository userRepository, final RoleRepository roleRepository){
        final Role user = newRole("ROLE_USER", Set.of());
        final Role admin = newRole("ROLE_ADMIN", Set.of(user));
        List<Role> roles = List.of(admin, user);
        roleRepository.saveAll(roles);

        List<User> users = List.of(newUser("montrey", Set.of()), newUser("user", Set.of(user)), newUser("admin", Set.of(admin)));
        userRepository.saveAll(users);
    }

    private Role newRole(final String code, final Set<Role> roles) {
        final Role role = new Role();
        role.setCode(code);
        role.setRoles(roles);
        return role;
    }

    private User newUser(final String uid, final Set<Role> roles) {
        final User user = new User();
        user.setUsername(uid);
        user.setPassword(passwordEncoder.encode("nimda"));
        user.setActive(true);
        user.setRoles(roles);
        return user;
    }
}
