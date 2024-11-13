package com.sandbox.mail.repository;

import com.sandbox.mail.domain.VerificationCode;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {

    // 특정 사용자의 기존 인증 코드를 삭제하는 메서드
    void deleteByEmail(String email);

    // 특정 사용자와 인증 코드가 일치하는지 확인하는 메서드 (인증 시 활용 가능)
    VerificationCode findByEmailAndVerificationCode(String email, String verificationCode);
}
