package com.sandbox.email.repository;

import com.sandbox.email.entity.EmailVerification;

import java.util.Optional;

public interface EmailRepository {
    void saveEmailVerification(EmailVerification info);

    Optional<EmailVerification> findByEmail(String email);

    void deleteInfoByEmail(String email);
}
