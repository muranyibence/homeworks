package com.muranyibence.webshop.exeption;

/**
 *
 * @author Bence
 */
public class DeviceNotExistInDBException extends RuntimeException {

    public DeviceNotExistInDBException() {
        //empty
    }

    public DeviceNotExistInDBException(String message) {

        super(message);
    }
}
