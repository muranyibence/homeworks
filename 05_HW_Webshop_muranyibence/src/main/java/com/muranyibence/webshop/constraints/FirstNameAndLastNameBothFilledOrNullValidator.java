/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muranyibence.webshop.constraints;

import com.muranyibence.webshop.UserEntity;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Bence
 */
public class FirstNameAndLastNameBothFilledOrNullValidator implements ConstraintValidator<FirstNameAndLastNameBothFilledOrNull, UserEntity> {

    @Override
    public void initialize(FirstNameAndLastNameBothFilledOrNull a) {
        //empty method
    }

    @Override
    public boolean isValid(UserEntity t, ConstraintValidatorContext cvc) {
        if ((t.getFirstname() == null && t.getLastname() == null) || (t.getFirstname() != null && t.getLastname() != null)) {
            return true;
        }
        return false;
    }

}
