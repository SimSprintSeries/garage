package com.sss.garage.service.auth.user.impl;

import java.util.Optional;

import com.sss.garage.model.user.User;
import com.sss.garage.model.user.UserRepository;
import com.sss.garage.service.auth.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SSSUserService implements UserService {

    private UserRepository userRepository;

    @Override
    public Optional<User> findUserByUsername(final String username) {
        return userRepository.findByUsername(username);
    }

    @Autowired
    public void setUserRepository(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
