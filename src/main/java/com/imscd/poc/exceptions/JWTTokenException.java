package com.imscd.poc.exceptions;

/**
 * Created by Christian Sperandio on 17/07/2016.
 */
public class JWTTokenException extends Exception {
    public JWTTokenException() {
    }

    public JWTTokenException(String message) {
        super(message);
    }

    public JWTTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public JWTTokenException(Throwable cause) {
        super(cause);
    }

    public JWTTokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
