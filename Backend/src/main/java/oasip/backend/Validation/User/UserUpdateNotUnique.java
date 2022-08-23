package oasip.backend.Validation.User;


import oasip.backend.Validation.Event.EventNonOverlabValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE , ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = UserUpdateNotUniqueValidator.class
)
public @interface UserUpdateNotUnique {
    String message() default "fdkfjkskjfljlkdjskfl";

    String field();

    String fieldMatch();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
