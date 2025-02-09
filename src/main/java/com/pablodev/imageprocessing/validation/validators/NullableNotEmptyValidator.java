package com.pablodev.imageprocessing.validation.validators;

import com.pablodev.imageprocessing.validation.annotations.NullableNotEmpty;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NullableNotEmptyValidator implements ConstraintValidator<NullableNotEmpty, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || !value.trim().isEmpty();
    }

}
