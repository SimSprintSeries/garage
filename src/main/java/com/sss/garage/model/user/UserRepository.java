package com.sss.garage.model.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<DiscordUser, Long> {
    Optional<DiscordUser> findByUsername(String userName);
    Optional<DiscordUser> findById(String id);
}
