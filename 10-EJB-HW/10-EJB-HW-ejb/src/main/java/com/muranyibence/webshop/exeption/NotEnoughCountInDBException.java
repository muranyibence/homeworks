package com.muranyibence.webshop.exeption;

/**
 *
 * @author Bence
 */
public class NotEnoughCountInDBException extends RuntimeException {

    public NotEnoughCountInDBException() {
        //empty
    }

    public NotEnoughCountInDBException(String message) {
        super(message);
    }

}
