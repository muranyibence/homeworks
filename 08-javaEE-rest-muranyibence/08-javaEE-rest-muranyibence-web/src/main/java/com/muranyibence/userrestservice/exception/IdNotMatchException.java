package com.muranyibence.userrestservice.exception;

/**
 *
 * @author Bence
 */
public class IdNotMatchException extends RuntimeException {

    public IdNotMatchException() {
        super();
    }

    public IdNotMatchException(String msg) {
        super(msg);
    }
}