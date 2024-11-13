package com.sandbox.email.controller;

import com.sandbox.email.dto.AuthenticationRequestDTO;
import com.sandbox.email.dto.AuthenticationResponseDTO;
import com.sandbox.email.dto.EmailRequestDTO;
import com.sandbox.email.dto.EmailResponseDTO;
import com.sandbox.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/email")
public class EmailController {
    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<?> sendVerificationCode(@RequestBody EmailRequestDTO request) {
        EmailResponseDTO response = emailService.sendVerificationCode(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/authentication")
    public ResponseEntity<?> verifyCode(@RequestBody AuthenticationRequestDTO request) {
        AuthenticationResponseDTO response = emailService.verifyCode(request);
        return ResponseEntity.ok(response);
    }
}
