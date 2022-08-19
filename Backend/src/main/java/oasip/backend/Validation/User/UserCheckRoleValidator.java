package oasip.backend.Validation.User;

import oasip.backend.Enitities.User;
import oasip.backend.Enum.Role;
import oasip.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserCheckRoleValidator implements ConstraintValidator<UserUniqueEmail, Enum> {


    @Override
    public boolean isValid(Enum s, ConstraintValidatorContext constraintValidatorContext) {
        for(Role r : Role.values()){
            if(s.equals(r))return true;
        }
        return false;
    }
}
