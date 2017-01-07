
package com.muranyibence.webshop;

/**
 *
 * @author Bence
 */
class UserAlreadyInDBException extends RuntimeException {

    public UserAlreadyInDBException() {
        //empty
    }

    public UserAlreadyInDBException(String message) {

        super(message);
    }
}
