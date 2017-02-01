package com.muranyibence.webshop.constraint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Bence
 */
public class AddressValidator implements ConstraintValidator<Address, String> {

    private static final Pattern REGEX_ZIP = Pattern.compile("(\\d){4}.*");

    @Override
    public void initialize(Address a) {
        //empty method
    }

    @Override
    public boolean isValid(String address, ConstraintValidatorContext cvc) {
        Matcher m = REGEX_ZIP.matcher(address);
        if ((address == null) || (m.find())) {
            return true;
        }
        return false;
    }

}
