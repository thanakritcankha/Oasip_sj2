package oasip.backend.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = UniqueNameCategoryValidator.class
)
public @interface UniqueNameCategory {
    String message() default "The eventCategoryName is NOT unique.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
