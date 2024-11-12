package com.sandbox.email.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/email")
public class EmailController {

    @PostMapping
    public ResponseEntity<?> sendEmail(@RequestBody String email) {
        return ResponseEntity.ok().body(email);
    }
}
