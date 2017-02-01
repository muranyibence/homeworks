package com.muranyibence.webshop.exeption;

/**
 *
 * @author Bence
 */
public class NoPrivilegeException extends RuntimeException {

    public NoPrivilegeException() {
        //emptya
    }

    public NoPrivilegeException(String message) {

        super(message);
    }
}
