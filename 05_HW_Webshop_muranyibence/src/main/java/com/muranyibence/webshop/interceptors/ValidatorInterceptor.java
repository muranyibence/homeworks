package com.muranyibence.webshop.interceptors;

import java.util.Optional;
import java.util.Set;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import com.muranyibence.webshop.annotations.Validate;
import com.muranyibence.webshop.annotations.ValidatorQualifier;
import com.muranyibence.webshop.exceptions.ValidationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.ExcludeClassInterceptors;

/**
 *
 * @author Bence
 */
@Interceptor
@BeanValidation
public class ValidatorInterceptor {

    @Inject
    @ValidatorQualifier
    private Validator validator;

    @AroundInvoke
    public Object checkMethodParameters(InvocationContext ic) throws Exception {
        if (!ic.getMethod().isAnnotationPresent(ExcludeClassInterceptors.class)) {
            validateParameters(ic.getParameters());
        }
        Logger.getLogger(ValidatorInterceptor.class.getName())
                .log(Level.INFO, "Checking method: {0}", ic.getMethod().getName());
        return ic.proceed();
    }

    private void validateParameters(Object[] parameters) {
        Validate validate;
        for (Object parameter : parameters) {
            validate = parameter.getClass().getAnnotation(Validate.class);
            if (null != validate) {
                validateBean(parameter);
            }
}
    }

    private void validateBean(Object o) {
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
