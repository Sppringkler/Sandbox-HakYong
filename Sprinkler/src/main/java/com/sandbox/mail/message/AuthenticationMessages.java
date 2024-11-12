package com.sandbox.mail.message;

import java.util.Random;

public class AuthenticationMessages {
    // 제목
    public static final String TITLE_MESSAGE = "[SANDBOX] 인증번호가 도착했습니다";

    // 고객에게 보내는 인사 메시지
    public static final String GREETING_MESSAGE = "안녕하세요, 고객님!";

    // 인증번호 메시지
    public static final String CERTIFICATION_MESSAGE = "인증번호는 다음과 같습니다:";

    // 감사 메시지
    public static final String FOOTER_MESSAGE = "감사합니다";

    public static int makeVerificationCode() {
        return 100000 + new Random().nextInt(900000); // 900,000까지의 값만 더해줌
    }
}
