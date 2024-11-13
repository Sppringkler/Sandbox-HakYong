package com.sandbox.email.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class AuthenticationRequestDTO {
    private String authentication;
    private String email;
}
