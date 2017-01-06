/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muranyibence.webshop.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author Bence
 */


@Target({ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = AddressValidator.class)
public @interface Address {
    String message() default "{Address.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
