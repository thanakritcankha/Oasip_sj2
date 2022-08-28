package oasip.backend.Controller;

import oasip.backend.DTOs.Authentication.LoginDTO;
import oasip.backend.DTOs.Category.CategoryListAllDto;
import oasip.backend.DTOs.User.UserCreateDto;
import oasip.backend.Service.AuthenticationService;
import oasip.backend.Service.EventCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/match")
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public boolean getAllCategory(@Valid @RequestBody LoginDTO oldUser){
        return service.match(oldUser);
    }
}
