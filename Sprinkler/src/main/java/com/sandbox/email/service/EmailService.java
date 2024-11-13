package com.sandbox.email.service;

import com.sandbox.email.dto.AuthenticationRequestDTO;
import com.sandbox.email.dto.AuthenticationResponseDTO;
import com.sandbox.email.dto.EmailRequestDTO;
import com.sandbox.email.dto.EmailResponseDTO;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    EmailResponseDTO sendVerificationCode(EmailRequestDTO email);

    AuthenticationResponseDTO verifyCode(AuthenticationRequestDTO request);

    String generateVerificationCode();

    SimpleMailMessage generateMailMessage(String to, String code);
}
