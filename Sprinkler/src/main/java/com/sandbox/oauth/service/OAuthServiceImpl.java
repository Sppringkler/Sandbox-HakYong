package com.sandbox.oauth.service;

import com.sandbox.oauth.dto.AccessTokenResponseDTO;
import com.sandbox.oauth.dto.TokenResponseDTO;
import com.sandbox.oauth.entity.User;
import com.sandbox.oauth.exception.AuthorizationCodeMissingException;
import com.sandbox.oauth.exception.AuthorizationEmptyTokenException;
import com.sandbox.oauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static com.sandbox.oauth.util.OAuthUtils.*;

@Service
@Transactional
@RequiredArgsConstructor
public class OAuthServiceImpl implements OAuthService{

    @Value("${spring.oauth.kakao.client-id}")
    private String clientId;

    @Value("${spring.oauth.kakao.redirect-uri}")
    private String redirectUri;

    @Value("${spring.oauth.kakao.token-url}")
    private String kakaoTokenUrl;

    @Value("${spring.oauth.kakao.user-info-url}")
    private String kakaoUserInfoUrl;

    private final UserRepository userRepository;

    @Override
    public TokenResponseDTO getAccessToken(String code) {
        if (code == null || code.isEmpty()) {
            throw new AuthorizationCodeMissingException("ERR_MISSING_AUTHORIZATION_CODE");
        }

        String tokenUrl = kakaoTokenUrl + "?grant_type=authorization_code" +
                "&client_id=" + clientId +
                "&redirect_uri=" + redirectUri +
                "&code=" + code;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String accessToken = extractAccessToken(response.getBody());
            String refreshToken = extractRefreshToken(response.getBody());
            saveUserInfo(accessToken, refreshToken);
            return new TokenResponseDTO(accessToken, refreshToken);
        } else {
            throw new RuntimeException("Failed to get access token");
        }
    }

    @Override
    public void saveUserInfo(String accessToken, String refreshToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(kakaoUserInfoUrl, HttpMethod.GET, entity, String.class);

        System.out.println(response.getBody());
        if (response.getStatusCode() == HttpStatus.OK) {
            String name = extractName(response.getBody());
            String email = extractEmail(response.getBody());
            User user = User.builder()
                    .name(name)
                    .email(email)
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();

            userRepository.save(user);
        } else {
            throw new RuntimeException("Failed to fetch user info");
        }
    }

    @Override
    public String getUserName(String accessToken) {
        if (accessToken.isEmpty()) {
            throw new AuthorizationEmptyTokenException("ERR_MISSING_ACCESS_TOKEN");
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(kakaoUserInfoUrl, HttpMethod.GET, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return extractName(response.getBody());
        } else {
            throw new RuntimeException("Fail to get User Name");
        }
    }

    @Override
    public AccessTokenResponseDTO reissueAccessToken(String refreshToken) {
        // Refresh Token 검증 및 Access Token 재발급
        Optional<User> userOptional = userRepository.findByRefreshToken(refreshToken);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("Invalid refresh token");
        }

        User user = userOptional.get();
        String newAccessToken = user.getAccessToken();

        return new AccessTokenResponseDTO(newAccessToken);
    }

    @Override
    public void logout(String refreshToken) {
        Optional<User> userOptional = userRepository.findByRefreshToken(refreshToken);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with the provided refresh token");
        }

        // 사용자 삭제
        User user = userOptional.get();
        userRepository.delete(user);
    }

}
