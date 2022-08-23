package oasip.backend.Validation.User;

import oasip.backend.DTOs.Category.CategoryCreateDto;
import oasip.backend.DTOs.Event.EventCreateDto;
import oasip.backend.DTOs.User.UserUpdateDto;
import oasip.backend.Enitities.User;
import oasip.backend.Validation.Event.EventNonOverlab;
import oasip.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;

public class UserUpdateNotUniqueValidator implements ConstraintValidator<UserUpdateNotUnique, UserUpdateDto> {
    @Autowired
    private UserRepository userRepository;


    @Override
    public boolean isValid(UserUpdateDto userUpdateDto, ConstraintValidatorContext constraintValidatorContext) {
        List<User> allUser = userRepository.findAll();
        User oldUser = userRepository.findById(userUpdateDto.getId()).orElseThrow(
                () -> new ResponseStatusException( HttpStatus.NOT_FOUND , userUpdateDto.getId() + " Does not Exist !!!"));
        List<User> result = allUser.stream().filter(
                v -> v.getId() != oldUser.getId()).collect(Collectors.toList());
        List<User> checkUnique = result.stream().filter(v -> {
            if(v.getName().toLowerCase().equals(userUpdateDto.getName().toLowerCase()) || v.getEmail().toLowerCase().equals(userUpdateDto.getEmail().toLowerCase())){
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        System.out.println("dsakldjlksja: "+ checkUnique.size());
        if(checkUnique.size() > 0){
            return false;
        }
        return true;
    }
}
