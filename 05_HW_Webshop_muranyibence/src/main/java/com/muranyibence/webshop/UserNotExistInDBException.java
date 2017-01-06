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
public class UserNotExistInDBException extends RuntimeException {

    public UserNotExistInDBException() {
        //empty
    }

    public UserNotExistInDBException(String message) {

        super(message);
    }
}
