package com.muranyibence.webshop.constraint;

import com.muranyibence.webshop.entities.UserEntity;
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
    public boolean isValid(UserEntity user, ConstraintValidatorContext cvc) {
        if (user.getDateOfBirth() == null) {
            return true;
        }
        if (user.getDateOfBirth().before(user.getRegistrationDate())) {
            return true;
        }
        return false;
    }

}
