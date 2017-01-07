package com.muranyibence.webshop;

/**
 *
 * @author Bence
 */
class DeviceAlreadyInDBException extends RuntimeException {

    public DeviceAlreadyInDBException() {
        //empty
    }

    public DeviceAlreadyInDBException(String message) {

        super(message);
    }
}
