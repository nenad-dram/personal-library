package com.endyary.perlibserver.misc;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Annotation which indicates that a field value must be one
 * of the given enum values
 *
 * @author Nenad Dramicanin
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValueOfEnumValidator.class)
public @interface ValueOfEnum {
    Class<? extends Enum<?>> enumClass();

    String message() default "invalid value provided";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}