package com.muranyibence.webshop.interceptor;

import com.muranyibence.webshop.exeption.ValidationException;
import com.muranyibence.webshop.exeption.annotation.Validate;
import java.util.Optional;
import java.util.Set;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.ExcludeClassInterceptors;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

/**
 *
 * @author Bence
 */
@Interceptor
@BeanValidation
public class ValidatorInterceptor {

    ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
    Validator validator = vf.getValidator();

    @AroundInvoke
    public Object checkMethodParameters(InvocationContext ic) throws ValidationException {

        if (!ic.getMethod().isAnnotationPresent(ExcludeClassInterceptors.class)) {
            validateParameters(ic.getParameters());
        }
        Logger.getLogger(ValidatorInterceptor.class.getName())
                .log(Level.INFO, "Checking method: {0}", ic.getMethod().getName());
        try {
            return ic.proceed();
        } catch (Exception ex) {
            Logger.getLogger(ValidatorInterceptor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void validateParameters(Object[] parameters) throws ValidationException {
        boolean validate;
        for (Object parameter : parameters) {
            validate = parameter.getClass().isAnnotationPresent(Validate.class);
            if (validate) {
                validateBean(parameter);
            }
        }
    }

    private void validateBean(Object o) throws ValidationException {
        Set<ConstraintViolation<Object>> violations = validator.validate(o);
        Optional<String> errorMessage = violations.stream()
                .map(e -> "Validation error: " + e.getMessage() + " on property: "
                + e.getPropertyPath().toString() + " . ")
                .reduce(String::concat);
        if (errorMessage.isPresent()) {
            throw new ValidationException(errorMessage.get());
        }
    }
}
