
package com.muranyibence.webshop;

/**
 *
 * @author Bence
 */
public class UserNotExistInDBException extends RuntimeException {

    public UserNotExistInDBException() {
        //empty
    }

    public UserNotExistInDBException(String message) {

        super(message);
    }
}
