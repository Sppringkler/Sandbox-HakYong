package com.sandbox.oauth.repository;

import com.sandbox.oauth.entity.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);

    Optional<User> findByRefreshToken(String refreshToken);

    void delete(User user);
}
