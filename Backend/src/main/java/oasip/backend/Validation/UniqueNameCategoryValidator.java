package oasip.backend.Validation;

import oasip.backend.Enitities.Eventcategory;
import oasip.backend.repositories.EventcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNameCategoryValidator implements ConstraintValidator<UniqueNameCategory, String> {
    @Autowired
    EventcategoryRepository eventcategoryRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Eventcategory result = eventcategoryRepository.findByEventCategoryName(s);
        if(result == null)return true;
        return false;
    }
}
