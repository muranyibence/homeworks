package com.muranyibence.webshop.exceptions;

/**
 *
 * @author Bence
 */
public class ValidationException extends Exception{
    
    public ValidationException(String msg) {
        super(msg);
    }

    public ValidationException() {
        super();
    }

}
