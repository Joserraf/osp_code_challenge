package com.osp.codechallenge.configuration.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PositionItemValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PositionItemConstraint {
    String message() default "Repeated position items";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}