package com.muranyibence.webshop.exceptions;

/**
 *
 * @author Bence
 */
public class DeviceAlreadyInDBException extends RuntimeException {

    public DeviceAlreadyInDBException() {
        //empty
    }

    public DeviceAlreadyInDBException(String message) {

        super(message);
    }
}
