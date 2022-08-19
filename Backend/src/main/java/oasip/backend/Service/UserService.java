package oasip.backend.Service;

import oasip.backend.DTOs.UserDTO.UserDetailDto;
import oasip.backend.DTOs.UserDTO.UserListDto;
import oasip.backend.Enitities.User;
import oasip.backend.ListMapper;
import oasip.backend.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper;

    public List<UserListDto> getAllUser(){
        List<User> userList = userRepository.findAll(Sort.by("name").ascending());
        return listMapper.maplist(userList, UserListDto.class,modelMapper);
    }

    public UserDetailDto getUser(Integer userId) {
        User user =  userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException(userId + " Does not Exist !!!" ));
        return modelMapper.map(user , UserDetailDto.class);
    }


}
