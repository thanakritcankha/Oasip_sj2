package oasip.backend.Validation.Category;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = CategoryUniqueNameValidator.class
)
public @interface CategoryUniqueName {
    String message() default "The eventCategoryName is NOT unique.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
