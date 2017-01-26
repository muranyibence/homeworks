package com.muranyibence.webshop.exceptions;

/**
 *
 * @author Bence
 */
public class NotEnoughCountInDBException extends Exception {

    public NotEnoughCountInDBException() {
        //empty
    }

    public NotEnoughCountInDBException(String message) {
        super(message);
    }
    
}
