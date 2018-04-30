package com.globalcrm.rest.exceptions;

public class AuthenticationException extends RuntimeException {
    private static final long serialVersionUID = -3824115531155458956L;

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}