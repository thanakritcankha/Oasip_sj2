package oasip.backend.Validation.User;

import oasip.backend.Enum.UserRole;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserCheckRoleValidator implements ConstraintValidator<UserCheckRole, String> {


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s.length()!=0){
            for(UserRole r : UserRole.values()){
                if(s.equals(r.toString()))
                    return true;
            }
        }else{
            return true;
        }
        return false;
    }
}
