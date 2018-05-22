package com.globalcrm.rest.exceptions;

/**
 * Created by Hugo Lezama on April - 2018
 */
public class BadLoginException extends RuntimeException {
    public BadLoginException() {
    }

    public BadLoginException(String message) {
        super(message);
    }

    public BadLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadLoginException(Throwable cause) {
        super(cause);
    }

    public BadLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
