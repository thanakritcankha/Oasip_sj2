package oasip.backend.Validation.User;

import oasip.backend.Enum.UserRole;

import javax.management.relation.Role;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserCheckRoleValidator implements ConstraintValidator<UserCheckRole, String> {


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println(s);
        for(UserRole r : UserRole.values()){
            if(s.equals(r.toString()))
                return true;
        }
        return false;
    }
}
