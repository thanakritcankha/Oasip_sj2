package oasip.backend.Validation.User;

import oasip.backend.DTOs.Category.CategoryCreateDto;
import oasip.backend.DTOs.Event.EventCreateDto;
import oasip.backend.DTOs.User.UserUpdateDto;
import oasip.backend.Enitities.User;
import oasip.backend.Validation.Event.EventNonOverlab;
import oasip.backend.repositories.UserRepository;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserUpdateNotUniqueValidator implements ConstraintValidator<UserUpdateNotUnique, UserUpdateDto> {
    @Autowired
    private UserRepository userRepository;

    private String field;
    private String fieldMatch;

    @Override
    public void initialize(UserUpdateNotUnique constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(UserUpdateDto userUpdateDto, ConstraintValidatorContext constraintValidatorContext) {
//        constraintValidatorContext.disableDefaultConstraintViolation();
//        constraintValidatorContext.buildConstraintViolationWithTemplate("dsa").addNode("name").addConstraintViolation();

        List<User> allUser = userRepository.findAll();
        User oldUser = userRepository.findById(userUpdateDto.getId()).orElseThrow(
                () -> new ResponseStatusException( HttpStatus.NOT_FOUND , userUpdateDto.getId() + " Does not Exist !!!"));
        List<User> result = allUser.stream().filter(
                v -> v.getId() != oldUser.getId()).collect(Collectors.toList());

        List<User> checkUniqueName = result.stream().filter(v -> {
            if(v.getName().toLowerCase().equals(userUpdateDto.getName().toLowerCase())){
                return true;
            }
            return false;
        }).collect(Collectors.toList());

        if(checkUniqueName.size() > 0){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("The Name must be unique.").addNode("name").addConstraintViolation();
        }

        List<User> checkUniqueEmail = result.stream().filter(v -> {
            if(v.getEmail().toLowerCase().equals(userUpdateDto.getEmail().toLowerCase())){
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        if(checkUniqueEmail.size() > 0){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("The email must be unique.").addNode("email").addConstraintViolation();
        }
        if(checkUniqueName.size() + checkUniqueEmail.size() > 0){
            return false;
        }
        return true;
    }
}
