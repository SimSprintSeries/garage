package com.sss.garage.dev.initial.data;

import java.util.HashSet;
import java.util.List;

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
                      final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        loadInitialData(userRepository);
    }

    public void loadInitialData(final UserRepository userRepository){

        List<User> users = List.of(newUser("montrey"), newUser("admin"), newUser("user"));

        userRepository.saveAll(users);
    }

    private User newUser(final String uid) {
        final User user = new User();
        user.setUsername(uid);
        user.setPassword(passwordEncoder.encode("nimda"));
        user.setActive(true);
        user.setRoles(new HashSet<>());
        return user;
    }
}
