package com.globalcrm.rest.exceptions;

/**
 * Created by Hugo Lezama on April - 2018
 */
public class MismatchException extends RuntimeException {
    public MismatchException() {
    }

    public MismatchException(String message) {
        super(message);
    }

    public MismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public MismatchException(Throwable cause) {
        super(cause);
    }

    public MismatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
