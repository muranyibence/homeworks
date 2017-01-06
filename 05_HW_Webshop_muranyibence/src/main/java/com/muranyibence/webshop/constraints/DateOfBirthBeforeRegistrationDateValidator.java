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
public class DateOfBirthBeforeRegistrationDateValidator implements ConstraintValidator<DateOfBirthBeforeRegistrationDate, UserEntity> {

    @Override
    public void initialize(DateOfBirthBeforeRegistrationDate a) {
        //empty method
    }

    @Override
    public boolean isValid(UserEntity t, ConstraintValidatorContext cvc) {
        if (t.getDateOfBirth().before(t.getRegistrationDate())) {
            return true;
        }
        return false;
    }

}
