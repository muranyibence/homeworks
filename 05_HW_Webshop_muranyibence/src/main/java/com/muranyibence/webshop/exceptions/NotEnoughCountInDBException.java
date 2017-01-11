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

    public NotEnoughCountInDBException(NotEnoughCountInDBException ex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
