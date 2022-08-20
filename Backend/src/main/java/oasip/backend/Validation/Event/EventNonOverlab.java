package oasip.backend.Validation.Event;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = EventNonOverlabValidator.class
)
public @interface EventNonOverlab {
    String message() default "Requested event overlapped with existing events;";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
