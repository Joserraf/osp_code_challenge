package com.osp.codechallenge.configuration.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Annotation interface to check the constraints of the position number.
 * Left here for learning purposes. Seems like it's not possible to make it work in a reactive environment.
 */
@Deprecated
@Documented
@Constraint(validatedBy = PositionItemValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PositionItemConstraint {
    String message() default "Repeated position items";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}