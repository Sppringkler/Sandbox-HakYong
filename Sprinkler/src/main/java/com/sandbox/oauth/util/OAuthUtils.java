package com.sandbox.oauth.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OAuthUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // accessToken 추출
    public static String extractAccessToken(String responseBody) {
        try {
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            JsonNode accessTokenNode = jsonNode.get("access_token");

            if (accessTokenNode == null) {
                throw new RuntimeException("Field 'access_token' not found in response");
            }

            return accessTokenNode.asText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse access token", e);
        }
    }

    // refreshToken 추출
    public static String extractRefreshToken(String responseBody) {
        try {
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            JsonNode refreshTokenNode = jsonNode.get("refresh_token");

            if (refreshTokenNode == null) {
                throw new RuntimeException("Field 'refresh_token' not found in response");
            }

            return refreshTokenNode.asText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse refresh token", e);
        }
    }

    public static String extractName(String responseBody) {
        try {
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            JsonNode nicknameNode = jsonNode.path("properties").path("nickname");
            if (nicknameNode.isEmpty()) {
                // 기본값 설정 (예: "익명 사용자")
                nicknameNode = jsonNode.path("kakao_account").path("profile").path("nickname");
                if (nicknameNode.isEmpty()) {
                    return "익명 사용자";  // 기본값
                }
            }
            return nicknameNode.asText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse user name", e);
        }
    }


    public static String extractEmail(String responseBody) {
        try {
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            JsonNode emailNode = jsonNode.path("kakao_account").path("email");
            if (emailNode.isEmpty()) {
                // 이메일이 없으면 기본값 설정
                return "이메일 정보 없음";  // 기본값
            }
            return emailNode.asText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse user email", e);
        }
    }

}
