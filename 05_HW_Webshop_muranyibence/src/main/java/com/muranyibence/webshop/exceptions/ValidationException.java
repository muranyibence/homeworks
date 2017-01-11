package com.muranyibence.webshop.exceptions;

/**
 *
 * @author Bence
 */
public class ValidationException extends RuntimeException{
    
    public ValidationException(String msg) {
        super(msg);
    }

    public ValidationException() {
        super();
    }

}
