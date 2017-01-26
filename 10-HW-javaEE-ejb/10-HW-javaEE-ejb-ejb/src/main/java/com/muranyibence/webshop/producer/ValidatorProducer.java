package com.muranyibence.webshop.producer;

import javax.enterprise.inject.Produces;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import com.muranyibence.webshop.annotations.ValidatorQualifier;

/**
 *
 * @author Bence
 */

public class ValidatorProducer {

    @Produces @ValidatorQualifier
    public Validator produceLogger() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        return vf.getValidator();
    }
}
