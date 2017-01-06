package com.muranyibence.webshop.constraints;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Bence
 */
public class AddressValidator implements ConstraintValidator<Address, String> {

    private static final String PATTERN = "(\\d){4}.*";

    @Override
    public void initialize(Address a) {
        //empty method
    }

    @Override
    public boolean isValid(String address, ConstraintValidatorContext cvc) {

        Pattern r = Pattern.compile(PATTERN);
        Matcher m = r.matcher(address);
        if ((address == null) || (m.find())) {
            return true;
        }
        return false;
    }

}
