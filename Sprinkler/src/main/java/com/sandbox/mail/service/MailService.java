package com.sandbox.mail.service;

import com.sandbox.mail.message.AuthenticationMessages;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MailService {
    // JavaMailSender는 Spring Boot에서 자동으로 빈(Bean)으로 등록해 주는 객체 중 하나
    @Autowired
    private JavaMailSender mailSender;

    // application.yml에 정의된 값을 주입할 때 사용
    @Value("${spring.mail.username}")
    private String fromMail;

    // 인증 코드와 이메일 임시로 저장.
    private ConcurrentHashMap<String,Integer> authCodeMap = new ConcurrentHashMap<>();

    public void sendVerificationCodeByEmail(String email){
        // 메일 발송 메세지 구조를 정의한 객체
        MimeMessage message = mailSender.createMimeMessage();

        // MimeMessage객체를 좀 더 쉽게 사용하기 위한 객체
        MimeMessageHelper messageHelper = new MimeMessageHelper(message,"UTF-8");

        try {
            Integer authCode = AuthenticationMessages.makeVerificationCode();
            //session.setAttribute("authCode",authCode);
            authCodeMap.put(email,authCode);

            messageHelper.setTo(email);
            messageHelper.setFrom(fromMail);
            messageHelper.setSubject(AuthenticationMessages.TITLE_MESSAGE);
            messageHelper.setText(
                     AuthenticationMessages.GREETING_MESSAGE + "\n"
                   + AuthenticationMessages.CERTIFICATION_MESSAGE + "\n"
                   + "[" + authCode + "]" + "\n"
                   + AuthenticationMessages.FOOTER_MESSAGE
            );
            mailSender.send(message);


        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean isAuthCodeValid(String email, int authCode){
        return Optional.ofNullable(authCodeMap.get(email)).orElse(-1) == authCode;
    }
}
