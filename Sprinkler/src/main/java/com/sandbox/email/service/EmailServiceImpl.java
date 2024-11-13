package com.sandbox.email.service;

import com.sandbox.email.dto.AuthenticationRequestDTO;
import com.sandbox.email.dto.AuthenticationResponseDTO;
import com.sandbox.email.dto.EmailRequestDTO;
import com.sandbox.email.dto.EmailResponseDTO;
import com.sandbox.email.entity.EmailVerification;
import com.sandbox.email.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{
    private final EmailRepository emailRepository;
    private final JavaMailSender mailSender;
    private final SecureRandom secureRandom = new SecureRandom();

    @Override
    public EmailResponseDTO sendVerificationCode(EmailRequestDTO request) {
        String code = generateVerificationCode();
        SimpleMailMessage message = generateMailMessage(request.getEmail(),code);
        mailSender.send(message);
        EmailVerification info = EmailVerification.builder()
                .email(request.getEmail())
                .code(code).build();
        emailRepository.saveInfo(info);
        return new EmailResponseDTO(true);
    }

    @Override
    public AuthenticationResponseDTO verifyCode(AuthenticationRequestDTO request) {
        Optional<EmailVerification> optionalVerification = emailRepository.findByEmail(request.getEmail());
        if(optionalVerification.isPresent()) {
            EmailVerification verification = optionalVerification.get();
            if(verification.getCode().equals(request.getAuthentication())){
                emailRepository.deleteInfoByEmail(verification.getEmail());
                return new AuthenticationResponseDTO(true);
            }
        }
        return new AuthenticationResponseDTO(false);
    }


    @Override
    public String generateVerificationCode() {
        int code = secureRandom.nextInt(900000) + 100000; // 100000 ~ 999999
        return String.valueOf(code);
    }

    @Override
    public SimpleMailMessage generateMailMessage(String to,String code) {
        String subject = "Verification Code";
        String text = "Your verification code is: " + code;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        return message;
    }
}
