package com.sandbox.oauth.service;

import com.sandbox.oauth.dto.AccessTokenResponseDTO;
import com.sandbox.oauth.dto.TokenResponseDTO;

public interface OAuthService {
    TokenResponseDTO getAccessToken(String code);

    void saveUserInfo(String accessToken, String refreshToken);

    String getUserName(String accessToken);

    AccessTokenResponseDTO reissueAccessToken(String refreshToken);

    void logout(String refreshToken);
}
