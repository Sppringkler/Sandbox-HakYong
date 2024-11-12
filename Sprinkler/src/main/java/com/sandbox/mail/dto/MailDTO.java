package com.sandbox.mail.dto;

import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MailDTO {
	String email;
	String authentication;
}
