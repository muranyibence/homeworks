package com.muranyibence.webshop.exceptions;

/**
 *
 * @author Bence
 */
public class NoPrivilegeException extends Exception {

    public NoPrivilegeException() {
        //empty
    }

    public NoPrivilegeException(String message) {

        super(message);
    }
}
