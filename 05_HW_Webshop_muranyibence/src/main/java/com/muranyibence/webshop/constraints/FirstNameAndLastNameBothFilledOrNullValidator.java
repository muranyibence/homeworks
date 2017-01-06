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
    public boolean isValid(UserEntity user, ConstraintValidatorContext cvc) {
        boolean isBothNull = user.getFirstname() == null && user.getLastname() == null;
        boolean isBothFilled = user.getFirstname() != null && user.getLastname() != null;
        return isBothNull || isBothFilled;
    }

}
