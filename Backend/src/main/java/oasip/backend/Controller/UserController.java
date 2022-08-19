package oasip.backend.Controller;

import oasip.backend.DTOs.UserDTO.UserDetailDto;
import oasip.backend.DTOs.UserDTO.UserListDto;
import oasip.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("")
    public List<UserListDto> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/{userId}")
    public UserDetailDto getUser(@PathVariable Integer userId){
        return userService.getUser(userId);
    }
}
