package oasip.backend.Validation.User;

import oasip.backend.Enitities.User;
import oasip.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserUniqueNameValidator implements ConstraintValidator<UserUniqueName, String> {
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        User result = userRepository.findByName(s);
        if(result == null)return true;
        return false;
    }
}
