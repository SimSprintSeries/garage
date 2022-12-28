package com.sss.garage.service.auth.user;

import java.util.Optional;

import com.sss.garage.model.user.User;

public interface UserService {
    Optional<User> findUserByUsername(final String Username);
}
