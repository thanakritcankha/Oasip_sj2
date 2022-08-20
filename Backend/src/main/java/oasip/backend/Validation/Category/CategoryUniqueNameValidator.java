package oasip.backend.Validation.Category;

import oasip.backend.Enitities.Eventcategory;
import oasip.backend.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryUniqueNameValidator implements ConstraintValidator<CategoryUniqueName, String> {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Eventcategory result = categoryRepository.findByEventCategoryName(s);
        if(result == null)return true;
        return false;
    }
}
