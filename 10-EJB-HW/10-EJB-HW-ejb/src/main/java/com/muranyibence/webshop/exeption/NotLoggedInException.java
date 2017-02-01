package com.muranyibence.webshop.exeption;

/**
 *
 * @author Bence
 */
public class NotLoggedInException extends RuntimeException {

    public NotLoggedInException() {
        //empty
    }

    public NotLoggedInException(String message) {

        super(message);
    }
}
