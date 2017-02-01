package com.muranyibence.webshop.exeption;

/**
 *
 * @author Bence
 */
public class AuthenticationFailureException extends RuntimeException {

    public AuthenticationFailureException() {
        //empty
    }

    public AuthenticationFailureException(String message) {

        super(message);
    }
}
