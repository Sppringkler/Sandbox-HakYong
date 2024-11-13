package com.sandbox.mail.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "verification_codes")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class VerificationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;   // 이메일
    private String verificationCode; // 인증코드
    private LocalDateTime expirationTime; //기간
    private boolean isVerified; // 인증여부
    private LocalDateTime createdAt = LocalDateTime.now(); // 생성시간
    private LocalDateTime updatedAt = LocalDateTime.now(); // 업데이트 시간
}
