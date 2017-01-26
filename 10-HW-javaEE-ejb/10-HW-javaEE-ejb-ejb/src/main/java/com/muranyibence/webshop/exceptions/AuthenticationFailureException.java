package com.muranyibence.webshop.exceptions;

/**
 *
 * @author Bence
 */
public class AuthenticationFailureException extends Exception {

    public AuthenticationFailureException() {
        //empty
    }

    public AuthenticationFailureException(String message) {

        super(message);
    }
}
