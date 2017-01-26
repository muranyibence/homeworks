package com.muranyibence.webshop.exceptions;

/**
 *
 * @author Bence
 */
public class NotLoggedInException extends Exception {

    public NotLoggedInException() {
        //empty
    }

    public NotLoggedInException(String message) {

        super(message);
    }
}
