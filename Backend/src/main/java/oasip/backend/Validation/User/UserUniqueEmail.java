package oasip.backend.Validation.User;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = UserUniqueEmailValidator.class
)
public @interface UserUniqueEmail {
    String message() default "The Email is not unique.";

    String fieldId();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
