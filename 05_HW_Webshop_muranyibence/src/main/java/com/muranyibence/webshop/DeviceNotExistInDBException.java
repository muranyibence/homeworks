/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muranyibence.webshop;

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
