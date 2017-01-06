/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    @Override
    public void initialize(Address a) {
      //empty method
    }

    @Override
    public boolean isValid(String t, ConstraintValidatorContext cvc) {
              String pattern = "(\\d){4}.*";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(t);
        if ((t == null) || (m.find())) {
            return true;
        }
        return false;
    }


}
