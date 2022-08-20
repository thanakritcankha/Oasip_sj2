package oasip.backend.Controller;


import oasip.backend.DTOs.Event.EventEditDto;
import oasip.backend.DTOs.User.UserCreateDto;
import oasip.backend.DTOs.User.UserDetailDto;
import oasip.backend.DTOs.User.UserListAllDto;
import oasip.backend.DTOs.User.UserUpdateDto;
import oasip.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("")
    public List<UserListAllDto> getAllUsers() { return userService.getAllUser(); }

    @GetMapping("/{userId}")
    public UserDetailDto getUserDetail(@PathVariable Integer userId) { return userService.getUser(userId); }

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public UserCreateDto createUser(@Valid @RequestBody UserCreateDto newUser) {
        return userService.createUser(newUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
    }

    @PatchMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserUpdateDto updateEvent(@PathVariable Integer userId, @Valid @RequestBody UserUpdateDto updateUser) {
        return userService.updateUser(updateUser, userId);
    }
}
