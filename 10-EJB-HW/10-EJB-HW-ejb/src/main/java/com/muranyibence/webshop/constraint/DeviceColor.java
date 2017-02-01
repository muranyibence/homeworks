package com.muranyibence.webshop.constraint;

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
@Constraint(validatedBy = DeviceColorValidator.class)
@Target({ElementType.TYPE})
@Retention(RUNTIME)
public @interface DeviceColor {

    String message() default "{DeviceColor.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
