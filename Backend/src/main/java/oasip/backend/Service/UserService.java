package oasip.backend.Service;

import oasip.backend.DTOs.Event.EventDetailDto;
import oasip.backend.DTOs.Event.EventListAllDto;
import oasip.backend.DTOs.User.UserCreateDto;
import oasip.backend.DTOs.User.UserDetailDto;
import oasip.backend.DTOs.User.UserListAllDto;
import oasip.backend.Enitities.Event;
import oasip.backend.Enitities.User;
import oasip.backend.ListMapper;
import oasip.backend.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper;

    public List<UserListAllDto> getAllUser() {
        List<User> userList = userRepository.findAll(Sort.by("name").ascending());
        return listMapper.maplist(userList, UserListAllDto.class, modelMapper);
    }

    public UserDetailDto getUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResponseStatusException( HttpStatus.NOT_FOUND , userId + " Does not Exist !!!"));
        return modelMapper.map(user, UserDetailDto.class);
    }

    public UserCreateDto createUser(UserCreateDto newUser) {
        User user = modelMapper.map(newUser, User.class);
        userRepository.saveAndFlush(user);
        return newUser;
    }
}

