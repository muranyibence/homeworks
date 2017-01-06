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
class DeviceAlreadyInDBException extends RuntimeException {

    public DeviceAlreadyInDBException() {
        //empty
    }

    public DeviceAlreadyInDBException(String message) {

        super(message);
    }
}
