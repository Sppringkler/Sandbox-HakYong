package com.sandbox.oauth.exception;

public class AuthorizationCodeMissingException extends RuntimeException{
    public AuthorizationCodeMissingException(String message) {
        super(message);
    }
}
