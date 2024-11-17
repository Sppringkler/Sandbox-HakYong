package com.sandbox.oauth.controller;

import com.sandbox.oauth.dto.AccessTokenResponseDTO;
import com.sandbox.oauth.dto.AuthCodeRequestDTO;
import com.sandbox.oauth.dto.TokenResponseDTO;
import com.sandbox.oauth.dto.UserInfoResponseDTO;
import com.sandbox.oauth.service.OAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
@Slf4j
public class OAuthController {
    private final OAuthService oAuthService;

    @PostMapping("/auth")
    public ResponseEntity<AccessTokenResponseDTO> handleAuthCode(@RequestBody AuthCodeRequestDTO request){
        TokenResponseDTO response = oAuthService.getAccessToken(request.getCode());
        return ResponseEntity.ok()
                .header("Set-Cookie", "refreshToken=" + response.getRefreshToken() + "; Path=/; HttpOnly; Secure; SameSite=None")
                .body(new AccessTokenResponseDTO(response.getAccessToken()));
    }

    @GetMapping("/member")
    public ResponseEntity<UserInfoResponseDTO> getUserInfo(@RequestHeader(value = "Authorization", required = false) String authorizationHeader){
        String accessToken = authorizationHeader.replace("Bearer ", "");
        String name = oAuthService.getUserName(accessToken);
        return ResponseEntity.ok(new UserInfoResponseDTO(name));
    }
}
