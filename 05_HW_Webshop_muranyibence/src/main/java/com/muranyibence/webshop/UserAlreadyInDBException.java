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
class UserAlreadyInDBException extends RuntimeException {

    public UserAlreadyInDBException() {
        //empty
    }

    public UserAlreadyInDBException(String message) {

        super(message);
    }
}
