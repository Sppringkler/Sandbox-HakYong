package com.sandbox.oauth.exception;

public class AuthorizationEmptyTokenException extends RuntimeException{
    public AuthorizationEmptyTokenException(String message) {
        super(message);
    }
}
