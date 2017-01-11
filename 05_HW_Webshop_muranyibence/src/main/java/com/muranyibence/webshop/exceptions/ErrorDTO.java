package com.muranyibence.webshop.exceptions;

/**
 *
 * @author Bence
 */
public class ErrorDTO {

    private String errorMessage;

    public ErrorDTO() {
        //empty
    }

    public ErrorDTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    
}
