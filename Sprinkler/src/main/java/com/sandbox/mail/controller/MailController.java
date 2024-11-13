package com.sandbox.mail.controller;


import com.sandbox.mail.domain.ResponseMessage;
import com.sandbox.mail.dto.EmailRequest;
import com.sandbox.mail.dto.MailDTO;
import com.sandbox.mail.service.MailService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/email")
@RequiredArgsConstructor
@RestController
public class MailController {
    private final MailService mailService;

    // @RequestBody String email : JSON 데이터가 문자열(String) 그대로 저장
    @PostMapping
    public Map<String, Object> sendEmail(@RequestBody EmailRequest emailRequest){
        //mailService.sendVerificationCodeByEmail(emailRequest.getEmail()); map으로.
        mailService.createOrUpdateVerificationCode(emailRequest.getEmail());
        Map<String, Object> response = new HashMap<>();
        response.put("isOk", true);

        //return ResponseEntity.ok(new ResponseMessage(true)); 이건 왜 안되지?
        return response;
    }

    @PostMapping("/authentication")
    public Map<String, Object> authentication(@RequestBody MailDTO mDto, HttpSession session) {
        Map<String, Object> response = new HashMap<>();

//        if(mailService.isAuthCodeValid(mDto.getEmail(), Integer.parseInt(String.valueOf(mDto.getAuthentication())))) {
//            response.put("isSuccess", true);
//        }else {
//            response.put("isSuccess", false);
//        }

        if(mailService.verifyCode(mDto.getEmail(), mDto.getAuthentication())) {
            response.put("isSuccess", true);
        }else {
            response.put("isSuccess", false);
        }

        return response;
    }

}
