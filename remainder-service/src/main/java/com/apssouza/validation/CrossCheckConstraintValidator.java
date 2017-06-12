package com.apssouza.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * A constraint ToDo validator
 *
 * @author apssouza
 */
public class CrossCheckConstraintValidator implements ConstraintValidator<CheckIsValid, ValidEntity> {

    @Override
    public void initialize(CheckIsValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(ValidEntity entity, ConstraintValidatorContext context) {
        return entity.isValid();
    }
}
