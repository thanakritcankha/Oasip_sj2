package oasip.backend.Controller;


import oasip.backend.DTOs.User.UserDetailDto;
import oasip.backend.DTOs.User.UserListAllDto;
import oasip.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

//    @PostMapping("")
//    @ResponseStatus(HttpStatus.OK)
//    public UserCreateDto createUser(@Valid @RequestBody UserCreateDto newUser) {
//        return userService.createUser(newUser);
//    }
}
