package com.apssouza.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Check if the instance is valid.
 * @author apssouza
 */
@Documented
@Constraint(validatedBy = CrossCheckConstraintValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckIsValid {

    String message() default "Cross check failed!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
