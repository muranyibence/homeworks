package com.muranyibence.webshop.exeption;

/**
 *
 * @author Bence
 */
public class UserAlreadyInDBException extends RuntimeException {

    public UserAlreadyInDBException() {
        //empty
    }

    public UserAlreadyInDBException(String message) {

        super(message);
    }
}
