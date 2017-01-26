
package com.muranyibence.webshop.exceptions;

/**
 *
 * @author Bence
 */
public class DeviceNotExistInDBException extends Exception {

    public DeviceNotExistInDBException() {
        //empty
    }

    public DeviceNotExistInDBException(String message) {

        super(message);
    }
}
