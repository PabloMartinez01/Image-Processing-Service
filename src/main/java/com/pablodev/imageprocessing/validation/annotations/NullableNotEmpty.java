package com.pablodev.imageprocessing.validation.annotations;

import com.pablodev.imageprocessing.validation.validators.NullableNotEmptyValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NullableNotEmptyValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NullableNotEmpty {

    String message() default "The field must be null or not empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
